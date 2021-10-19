package com.example.santiye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.adapter.OperatorRecyclerView
import com.example.santiye.adapter.WarehomeProductsRecyclerAdapter
import com.example.santiye.databinding.ActivityOperatorBinding
import com.example.santiye.product.OperatorDuty
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class Operator : AppCompatActivity() {

    private lateinit var binding: ActivityOperatorBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: OperatorRecyclerView
    private lateinit var dutyList: ArrayList<OperatorDuty>
    private lateinit var recyclerView: RecyclerView
    private  val TAG = "Operator"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperatorBinding.inflate(layoutInflater)

        firestore = Firebase.firestore

        recyclerView = binding.operatorRecyclerView
        dutyList = ArrayList<OperatorDuty>()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)


        getWork()



        adapter = OperatorRecyclerView(dutyList, this)
        recyclerView.adapter = adapter





        setContentView(binding.root)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.operator, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.r_menü) {

            adapter = OperatorRecyclerView(dutyList, this)
            recyclerView.adapter = adapter
        }

        return super.onOptionsItemSelected(item)
    }

    fun getWork(){

        var email = "deneme@bekoloader"
        firestore.collection("machine").get().addOnSuccessListener {
            for (doc in it){
                if (doc.get("email") as String == email){
                    val name =  doc.get("name") as String

                    firestore.collection(name).orderBy("data", Query.Direction.DESCENDING).get().addOnSuccessListener {

                        for (docd in it.documents){
                            dutyList.add(OperatorDuty("${docd.get("date2") as String}, ${docd.get("date2") as String}", docd.get("date1") as String, docd.get("date2") as String, docd.id,doc.get("name") as String))

                        }



                    }

                }
            }
            adapter.notifyDataSetChanged()
        }
    }
}