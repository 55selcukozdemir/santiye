package com.example.santiye

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.santiye.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLoginBinding
    public val TAG:String = "deneme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginGiris.setOnClickListener(){
            val emailT = binding.loginEditTextEmail.text.toString()
            if (emailT == "ana@deneme.com"){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else if(emailT == "warehome@deneme.com"){
                val intent = Intent(this,Warehome::class.java)
                startActivity(intent)
            }else if (emailT == "operator@deneme.com"){
                val intent = Intent(this, Operator::class.java)
                startActivity(intent)
            }

        }
    }
}