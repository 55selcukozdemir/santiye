package com.example.santiye

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.santiye.databinding.ActivityLoginBinding
import com.example.santiye.product.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {


    val PREFS_FILENAME = "com.ekremh.prefs"
    val MAIL = "MAIL"
    val DUTY = "DUTY"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var postArrayList: ArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)


        auth = Firebase.auth
        firestore = Firebase.firestore
        val curretUser = auth.currentUser
        postArrayList = ArrayList<Users>()


        val prefences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefences.edit()


        // Kullanıcı kontorlü var mı? yok mu?

        if(curretUser != null ){

            if (prefences.getString(MAIL, "mail") == curretUser.email) {

                if (prefences.getString(DUTY, "duty") == "depo") {
                    val i = Intent(this, Warehome::class.java)
                    startActivity(i)
                    finish()
                } else if (prefences.getString(DUTY, "duty") == "operaror") {
                    val i = Intent(this, Operator::class.java)
                    startActivity(i)
                    finish()
                } else if (prefences.getString(DUTY, "duty") == "ana") {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }else{
            setContentView(binding.root)
        }

        binding.sginin.setOnClickListener(View.OnClickListener {
            val email = binding.loginEditTextEmail.text.toString()
            val pass = binding.loginEditTextPass.text.toString()
            if (email.equals("") || pass.equals("")) {
                Toast.makeText(this, "Email ve şifre kısmı boş bırakılamaz!", Toast.LENGTH_LONG)
                    .show()
            } else {

                auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener {
                    firestore.collection("users").get().addOnSuccessListener { result ->
                        for (documnet in result) {
                            if ((documnet.get("email") as String) == email) {
                                editor.putString(MAIL, documnet.get("email") as String)
                                if (documnet.get("position") == "depo") {
                                    val i = Intent(this, Warehome::class.java)
                                    startActivity(i)
                                    editor.putString(DUTY, "depo")
                                    editor.apply()

                                    finish()
                                } else if (documnet.get("position") == "operaror") {
                                    val i = Intent(this, Operator::class.java)
                                    startActivity(i)
                                    editor.putString(DUTY, "operaror")
                                    editor.apply()
                                    finish()
                                } else if (documnet.get("position") == "ana") {
                                    val i = Intent(this, MainActivity::class.java)
                                    startActivity(i)
                                    editor.putString(DUTY, "ana")
                                    editor.apply()
                                    finish()
                                }
                            }
                        }
                    }
                }
            }
        })

    }
}