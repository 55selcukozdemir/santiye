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

class EquipmentFragment : Fragment() {

    private lateinit var binding: FragmentMainEquipmentBinding

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentMainEquipmentBinding.inflate(inflater, container, false)

        val recyclerView = binding.mainEquipmentRecyclerView
        val contentList = ArrayList<Equipment>()
        contentList.add(Equipment(R.drawable.`in`,"12.10.21  15.15","12.10.21  16.15"))
        contentList.add(Equipment(R.drawable.`in`,"12.10.21  15.15","12.10.21  16.15"))
        contentList.add(Equipment(R.drawable.`in`,"12.10.21  15.15","12.10.21  16.15"))
        contentList.add(Equipment(R.drawable.`in`,"12.10.21  15.15","12.10.21  16.15"))
        contentList.add(Equipment(R.drawable.`in`,"12.10.21  15.15","12.10.21  16.15"))

        recyclerView.layoutManager = LinearLayoutManager(inflater.context, LinearLayoutManager.VERTICAL,false)

        val adapter = MainEquipmentRecyclerAdapter(contentList,inflater.context)

        recyclerView.adapter = adapter
        Toast.makeText(activity,"mesaj", Toast.LENGTH_LONG).show()



        return binding.root

    }


}