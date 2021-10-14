package com.example.santiye.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.adapter.MainEquipmentPRecyclerAdapter
import com.example.santiye.databinding.FragmentEquipmentPBinding
import com.example.santiye.product.EquipmentP
import java.util.zip.Inflater

class EquipmentPActivity : AppCompatActivity() {

    private lateinit var binding: FragmentEquipmentPBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentEquipmentPBinding.inflate(layoutInflater)
        setContentView(binding.root)







        val recyclerView = binding.fragmentEquipmentPRecyclerView

        val equipmentpList = ArrayList<EquipmentP>()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        equipmentpList.add(EquipmentP("12.12.21  15:15","12.12.21  16:15"))
        equipmentpList.add(EquipmentP("12.12.21  15:15","12.12.21  16:15"))
        equipmentpList.add(EquipmentP("12.12.21  15:15","12.12.21  16:15"))
        equipmentpList.add(EquipmentP("12.12.21  15:15","12.12.21  16:15"))

        val adapter = MainEquipmentPRecyclerAdapter(equipmentpList)
        recyclerView.adapter = adapter

    }

}