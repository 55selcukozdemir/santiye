package com.example.santiye.warehome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.santiye.R
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


        ArrayAdapter.createFromResource(inflater.context, R.array.units, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = it
        }

        binding.arragametAddButtn.setOnClickListener(View.OnClickListener {
            val productName = binding.arrangametName.text
            val productAdded = binding.arrangametProductAdd.text

            if (productAdded.toString() != "" || productName.toString() != ""){

                val productMap = hashMapOf<String, Any>()
                productMap.put("name", productName.toString())
                productMap.put("size", productAdded.toString())
                productMap.put("unit", binding.spinner.selectedItem)
                productMap.put("date", Timestamp.now())

                firestore.collection("product").add(productMap).addOnSuccessListener {
                    Toast.makeText(context, "(${productName.toString()}) Ürün eklendi.", Toast.LENGTH_LONG).show()
                    productName.clear()
                    productAdded.clear()
                }.addOnFailureListener{
                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                }


            }else{
                Toast.makeText(inflater.context, "Lütfen boş olan yerleri doldurunuz.", Toast.LENGTH_SHORT).show()
            }
            
            
        })




        return binding.root





    }
}