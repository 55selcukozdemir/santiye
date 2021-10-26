package com.example.santiye.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.SupportActionModeWrapper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.R
import com.example.santiye.adapter.MainEquipmentRecyclerAdapter
import com.example.santiye.databinding.FragmentMainEquipmentBinding
import com.example.santiye.product.Equipment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EquipmentFragment : Fragment() {

    private lateinit var binding: FragmentMainEquipmentBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentMainEquipmentBinding.inflate(inflater, container, false)

        firestore = Firebase.firestore

        val recyclerView = binding.mainEquipmentRecyclerView
        val contentList = ArrayList<Equipment>()


        firestore.collection("machine").get().addOnSuccessListener {
            for (doc in it){
                contentList.add(Equipment(doc.get("imageUrl") as String, doc.get("name") as String))
            }
            recyclerView.layoutManager = LinearLayoutManager(inflater.context, LinearLayoutManager.VERTICAL,false)
            val adapter = MainEquipmentRecyclerAdapter(contentList,inflater.context)
            recyclerView.adapter = adapter
        }.addOnFailureListener {
            Toast.makeText(inflater.context, it.localizedMessage, Toast.LENGTH_LONG).show()
        }





//        Toast.makeText(activity,"mesaj", Toast.LENGTH_LONG).show()



        return binding.root

    }


}