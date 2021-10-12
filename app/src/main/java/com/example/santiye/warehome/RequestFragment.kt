package com.example.santiye.warehome

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.adapter.WarehomeRequestRecyclerAdapter
import com.example.santiye.databinding.FragmentRequestBinding
import com.example.santiye.product.Content
import com.example.santiye.product.RequestL
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RequestFragment : Fragment() {

    private lateinit var binding: FragmentRequestBinding
    private lateinit var requestList: ArrayList<RequestL>
    private lateinit var firestore: FirebaseFirestore
    private lateinit var customAdapter: WarehomeRequestRecyclerAdapter


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRequestBinding.inflate(inflater,container,false)

        val recyclerView = binding.warehomeRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        requestList = ArrayList<RequestL>()
        firestore = Firebase.firestore
        addRequest()


        customAdapter = WarehomeRequestRecyclerAdapter(requestList)
        recyclerView.adapter = customAdapter
        return binding.root

    }

    fun addRequest(){

//        .whereEqualTo("confirmation","null")
        firestore.collection("Request").addSnapshotListener { value, error ->
            requestList.clear()
            if (error != null){
                Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){
                        val documnet = value.documents

                        for (d in documnet){

//                            if (d.get("confirmation") as String == "null"){

                                val blok = d.get("block") as String
                                val confirmation = d.get("confirmation") as String
                                val floor = d.get("floor") as String
                                val meterial = d.get("meteral") as String
                                val quentity = d.get("quentity") as String
                                val id = d.id

                                requestList.add(RequestL(blok, confirmation, floor, meterial, quentity, id))
//                            }
                        }

                        customAdapter.notifyDataSetChanged()

                    }
                }
            }
        }





    }
}