package com.example.santiye.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.adapter.MainMeterialRecyclerAdapter
import com.example.santiye.databinding.FragmentMainMeterialBinding
import com.example.santiye.product.Meterial
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MeterialFragment : Fragment() {

    private lateinit var binding: FragmentMainMeterialBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var contentMeterial: ArrayList<Meterial>
    private lateinit var recyclerAdapter: MainMeterialRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMeterialBinding.inflate(inflater, container, false)

        val recyclerView1 = binding.fragmentMainMeterialRecyclerView
        contentMeterial = ArrayList()

        firestore = Firebase.firestore

        getData()


        binding.buttonRequest.setOnClickListener(View.OnClickListener {
            val blok = binding.meterialBolckText.text.toString()
            val floor = binding.meterialFloorText.text.toString()
            val meterial = binding.meterialMeterialText.text.toString()
            val quentity = binding.meterialQuentityText.text.toString()

            val requestMap = hashMapOf<String, Any>()
            requestMap.put("block", blok)
            requestMap.put("floor", floor)
            requestMap.put("meteral", meterial)
            requestMap.put("quentity", quentity)
            requestMap.put("confirmation", "true")

            firestore.collection("Request").add(requestMap).addOnSuccessListener {
                Toast.makeText(context, "İstekte bulunuldu!", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })

        recyclerView1.layoutManager =
            LinearLayoutManager(inflater.context, LinearLayoutManager.VERTICAL, false)

        recyclerAdapter = MainMeterialRecyclerAdapter(contentMeterial)
        recyclerView1.adapter = recyclerAdapter

        return binding.root


    }

    fun getData(){

        firestore.collection("Request").addSnapshotListener { value, error ->

            contentMeterial.clear()
            if (error != null){
                Toast.makeText(context, error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{

                if (value != null){

                    if (!value.isEmpty){

                        val doc = value.documents
                        for (d in doc){
                            val confirmation = d.get("confirmation").toString()
                            if (confirmation != "null"){
                                val block = d.get("block") as String
                                val floor = d.get("floor") as String
                                val meterial = d.get("meteral") as String
                                val quentity = d.get("quentity") as String
                                contentMeterial.add(Meterial(block,floor,meterial,quentity,confirmation))
                            }
                        }
                        recyclerAdapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }
}