package com.example.santiye

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

    private lateinit var binding: ActivityLoginBinding
    private val TAG: String = "deneme"
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var postArrayList: ArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        firestore = Firebase.firestore
        val curretUser = auth.currentUser
        postArrayList = ArrayList<Users>()


//        if(curretUser != null ){
//         val i = Intent(this, Warehome::class.java)
//           startActivity(i)
//            finish()
//         }


//
//        binding.sginup.setOnClickListener(View.OnClickListener {
//
//            val email = binding.loginEditTextEmail.text.toString()
//            val pass = binding.loginEditTextPass.text.toString()
//
//
//            if (email.equals("") || pass.equals("")) {
//                Toast.makeText(this, "Email Ve şifre kısmı boş bırakılamaz!", Toast.LENGTH_LONG)
//                    .show()
//            } else {
//                auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
//
//                    val userFeatures = hashMapOf("email" to email, "position" to postion)
//                    firestore.collection("Users").add(userFeatures).addOnSuccessListener {
//
//                        Log.d(TAG, "onCreate: kayıt yönlendirme $postion")
//                        if (postion == "depo") {
//                            val i = Intent(this, Warehome::class.java)
//                            startActivity(i)
//                            finish()
//                        } else if (postion == "operaror") {
//                            val i = Intent(this, Operator::class.java)
//                            startActivity(i)
//                            finish()
//                        } else if (postion == "ana") {
//                            val i = Intent(this, MainActivity::class.java)
//                            startActivity(i)
//                            finish()
//                        }
//                    }
//                }.addOnFailureListener {
//
//                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
//                }
//            }
//        })


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
                                if (documnet.get("position") == "depo") {
                                    val i = Intent(this, Warehome::class.java)
                                    startActivity(i)
                                    finish()
                                } else if (documnet.get("position") == "operaror") {
                                    val i = Intent(this, Operator::class.java)
                                    startActivity(i)
                                    finish()
                                } else if (documnet.get("position") == "ana") {
                                    val i = Intent(this, MainActivity::class.java)
                                    startActivity(i)
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