package com.example.santiye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.adapter.OperatorRecyclerView
import com.example.santiye.databinding.ActivityOperatorBinding
import com.example.santiye.product.OperatorDuty

class Operator : AppCompatActivity() {

    private lateinit var binding: ActivityOperatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperatorBinding.inflate(layoutInflater)

        val recyclerView = binding.operatorRecyclerView
        val listItem = ArrayList<OperatorDuty>()
        listItem.add(OperatorDuty("okul","15:00", "16:00"))
        listItem.add(OperatorDuty("okul","15:00", "16:00"))
        listItem.add(OperatorDuty("okul","15:00", "16:00"))
        listItem.add(OperatorDuty("okul","15:00", "16:00"))
        listItem.add(OperatorDuty("okul","15:00", "16:00"))
        listItem.add(OperatorDuty("okul","15:00", "16:00"))

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        val adapterCustom = OperatorRecyclerView(listItem,this)
        recyclerView.adapter = adapterCustom




        setContentView(binding.root)

    }
}