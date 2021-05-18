package com.example.projectweek17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectweek17.FullAPI.APIService
import com.example.projectweek17.FullAPI.Constant
import com.example.projectweek17.Model.User
import com.example.projectweek17.Response.SingleRespon
import com.example.projectweek17.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Logout()


    }
    private fun Logout(){
        binding.ButtonLogout.setOnClickListener {
            Constant.DelToken(this)
            startActivity(Intent(this,IsLogin::class.java))
            finish()

        }
    }


}