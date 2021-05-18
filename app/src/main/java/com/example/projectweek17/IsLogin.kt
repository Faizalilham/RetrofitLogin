package com.example.projectweek17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.projectweek17.FullAPI.APIService
import com.example.projectweek17.FullAPI.Constant
import com.example.projectweek17.Model.User
import com.example.projectweek17.Response.SingleRespon
import com.example.projectweek17.databinding.ActivityIsLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IsLogin : AppCompatActivity() {
    private lateinit var binding : ActivityIsLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIsLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SignUp()
        ButtonSignIn()


    }
    override fun onResume() {
        super.onResume()
        IsLogin()

    }
    private fun SignUp(){
        binding.ButtonSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
    }
    private fun ReqLogin(){
        val username = binding.ETUsername.text.toString()
        val password = binding.ETPassword.text.toString()
        APIService.APIEndpoint().SignIn(username, password).enqueue(object :
            Callback<SingleRespon<User>> {
            override fun onResponse(call: Call<SingleRespon<User>>, response: Response<SingleRespon<User>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        Constant.SetToken(this@IsLogin,body.data.token)
                        Toast.makeText(applicationContext," Hii ${body.data.name}", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@IsLogin,MainActivity::class.java))
                        finish()
                    }
                }else{
                    Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SingleRespon<User>>, t: Throwable) {
                println(t.message)
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun IsLogin(){
        val token = Constant.GetToken(this)
        if(!token.equals("Undefined")){
            startActivity(Intent(this,MainActivity::class.java).also {
                finish()
            })

        }
    }
    private fun ButtonSignIn(){
        binding.ButtonSignIn.setOnClickListener {
            ReqLogin()

        }
    }
}