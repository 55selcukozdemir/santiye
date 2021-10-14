package com.example.santiye.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.R
import com.example.santiye.adapter.MainMeterialRecyclerAdapter
import com.example.santiye.databinding.FragmentMainMeterialBinding
import com.example.santiye.product.Meterial
import com.example.santiye.warehome.SpinnerAdd
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MeterialFragment : Fragment() {

    private lateinit var binding: FragmentMainMeterialBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var contentMeterial: ArrayList<Meterial>
    private lateinit var recyclerAdapter: MainMeterialRecyclerAdapter
    private lateinit var spinnerList : ArrayList<String>
    private  val TAG = "MeterialFragment"


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
        getSpinner(inflater)




        binding.buttonRequest.setOnClickListener(View.OnClickListener {
            val blok = binding.meterialBolckSpinner.selectedItem
            val floor = binding.meterialFloorSpinner.selectedItem
            val meterial = binding.meterialMeterialSpinner.selectedItem
            val quentity = binding.meterialQuentityText.text.toString()

            val requestMap = hashMapOf<String, Any>()
            requestMap.put("block", blok)
            requestMap.put("floor", floor)
            requestMap.put("meteral", meterial)
            requestMap.put("quentity", quentity)
            requestMap.put("confirmation", "null")
            requestMap.put("date", Timestamp.now())

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

                                val block = d.get("block") as String
                                val floor = d.get("floor") as String
                                val meterial = d.get("meteral") as String
                                val quentity = d.get("quentity") as String
                                val id = d.id
                                contentMeterial.add(Meterial(block,floor,meterial,quentity,confirmation, id))

                        }
                        recyclerAdapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }

    fun getSpinner (inflater: LayoutInflater) {

        val meterialList = ArrayList<String>()
        firestore.collection("product").addSnapshotListener{ value, error ->
            if (error != null){
                Toast.makeText(inflater.context, error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){
                        val document = value.documents
                        for (d in document){
                            meterialList.add(d.get("name") as String)
                        }
                        val meterialAdapter = ArrayAdapter<String>(inflater.context,android.R.layout.simple_spinner_dropdown_item,meterialList)
                        binding.meterialMeterialSpinner.adapter = meterialAdapter
                    }
                }
            }
        }



        val blockList = ArrayList<String>()
        firestore.collection("block").addSnapshotListener{ value, error ->
            if (error != null){
                Toast.makeText(inflater.context, error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){
                        val document = value.documents
                        for (d in document){
                            blockList.add(d.get("name") as String)

                        }
                        val blockAdapter = ArrayAdapter<String>(inflater.context,android.R.layout.simple_spinner_dropdown_item,blockList)
                        binding.meterialBolckSpinner.adapter = blockAdapter
                    }
                }
            }
        }


        val floorList = ArrayList<String>()
        firestore.collection("floor").addSnapshotListener{ value, error ->
            if (error != null){
                Toast.makeText(inflater.context, error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){
                        val document = value.documents
                        for (d in document){
                            floorList.add(d.get("name") as String)

                        }
                        val floorAdapter = ArrayAdapter<String>(inflater.context,android.R.layout.simple_spinner_dropdown_item,floorList)
                        binding.meterialFloorSpinner.adapter = floorAdapter
                    }
                }
            }
        }






    }



}