package com.example.santiye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.adapter.OperatorRecyclerView
import com.example.santiye.adapter.WarehomeProductsRecyclerAdapter
import com.example.santiye.databinding.ActivityOperatorBinding
import com.example.santiye.product.OperatorDuty
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class Operator : AppCompatActivity() {

    private lateinit var binding: ActivityOperatorBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: OperatorRecyclerView
    private lateinit var dutyList: ArrayList<OperatorDuty>
    private  val TAG = "Operator"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperatorBinding.inflate(layoutInflater)

        firestore = Firebase.firestore

        val recyclerView = binding.operatorRecyclerView
        dutyList = ArrayList<OperatorDuty>()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)


        var email = "deneme@bekoloader"

        firestore.collection("machine").get().addOnSuccessListener {

            Log.d(TAG, "onCreate: machine")
            for (doc in it){
                if (doc.get("email") as String == email){
                    val name =  doc.get("name") as String

                    firestore.collection(name).get().addOnSuccessListener {

                        for (docd in it.documents){
                            dutyList.add(OperatorDuty("${docd.get("date2") as String}, ${docd.get("date2") as String}", docd.get("date1") as String, docd.get("date2") as String))
                        }

                        adapter = OperatorRecyclerView(dutyList, this)
                        recyclerView.adapter = adapter
                        Log.d(TAG, "onCreate: operator ${doc.data}")
                    }
                }
            }
        }









        setContentView(binding.root)

    }
}