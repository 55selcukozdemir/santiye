package com.example.santiye.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.R
import com.example.santiye.adapter.MainHomeRecyclerAdapter
import com.example.santiye.databinding.FragmentMainProfileBinding
import com.example.santiye.product.Content
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Profile : Fragment() {

    private lateinit var binding: FragmentMainProfileBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var email: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentMainProfileBinding.inflate(layoutInflater)

        val recyclerView = binding.fragmentMainProfileRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        firestore = Firebase.firestore
        email = "w@mail.com"
        getProfile()

        val contentList = ArrayList<Content>()
        firestore.collection("posts").whereEqualTo("email", email).get().addOnSuccessListener {
            for (i in it){
                val block = i.get("block") as String
                val contentImage = i.get("contentImage") as String
                val explanation = i.get("explanation") as String
                val floor = i.get("floor") as String
                val ticket = i.get("ticket") as String

                val timestamp = i["date"] as com.google.firebase.Timestamp
                val milliseconds =
                    timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000
                val sdf = SimpleDateFormat("MM/dd/yyyy")
                val netDate = Date(milliseconds)
                val date = sdf.format(netDate).toString()

                contentList.add(Content(email,floor,block, contentImage, explanation,ticket,date))
            }

            val adapter = MainHomeRecyclerAdapter(contentList)
            recyclerView.adapter = adapter
        }

        binding.button3.setOnClickListener(View.OnClickListener {
            val i = Intent(activity,ProductAdded::class.java)
            startActivity(i)
        })

        return binding.root
    }

    fun getProfile(){
        firestore.collection("users").whereEqualTo("email", email).get().addOnSuccessListener {
            for (i in it){
                binding.textView6.text = (i.get("name") as String + " " + i.get("lastName") as String)
                Picasso.get().load(i.get("userImage") as String).into(binding.imageView)
            }
        }
    }

}