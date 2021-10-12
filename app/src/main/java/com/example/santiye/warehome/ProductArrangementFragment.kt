package com.example.santiye.warehome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.santiye.databinding.FragmentProductArrangemetBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductArrangementFragment : Fragment() {

    private lateinit var binding: FragmentProductArrangemetBinding
    private lateinit var firestore: FirebaseFirestore


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProductArrangemetBinding.inflate(inflater,container,false)
        firestore = Firebase.firestore

        binding.arragametAddButtn.setOnClickListener(View.OnClickListener {
            val productName = binding.arrangametName.text.toString()
            val productAdded = binding.arrangametProductAdd.text.toString()
            val productUnit = binding.arrangametUnit.text.toString()

            val productMap = hashMapOf<String, Any>()
            productMap.put("name", productName)
            productMap.put("size", productAdded)
            productMap.put("unit", productUnit)
            productMap.put("date", Timestamp.now())

            firestore.collection("product").add(productMap).addOnSuccessListener {
                Toast.makeText(context, "($productName) Ürün eklendi.", Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
            }




        })




        return binding.root





    }
}