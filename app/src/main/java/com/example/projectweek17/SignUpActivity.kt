package com.example.projectweek17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.projectweek17.FullAPI.APIService
import com.example.projectweek17.Model.User
import com.example.projectweek17.Response.SingleRespon
import com.example.projectweek17.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ButtonSignUp()
    }
    private fun Register(){
        val name = binding.ETNameSignUp.text.toString()
        val username = binding.ETUsernameSignUp.text.toString()
        val email = binding.ETEmailSignUp.text.toString()
        val password = binding.ETPasswordSignUp.text.toString()
        APIService.APIEndpoint().SignUp(name,username,email,password)
                .enqueue(object : Callback<SingleRespon<User>>{
                    override fun onResponse(call: Call<SingleRespon<User>>, response: Response<SingleRespon<User>>) {
                        if(response.isSuccessful){
                            val body = response.body()
                            if(body != null){
                                Toast.makeText(applicationContext,body.msg,Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            val errorBody = response.errorBody().toString()
                            val code = response.code()
                            Log.e("xxxxx", errorBody )
                            Toast.makeText(applicationContext,code.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<SingleRespon<User>>, t: Throwable) {
                        println(t.message)
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
                    }
                })
    }
    private fun ButtonSignUp(){
        binding.ButtonSignUp.setOnClickListener {
            Register()
            startActivity(Intent(this,IsLogin::class.java))
            finish()
        }
    }
}