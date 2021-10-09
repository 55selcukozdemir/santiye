package com.example.santiye.warehome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.adapter.WarehomeProductsRecyclerAdapter
import com.example.santiye.databinding.FragmentProductArrangemetBinding
import com.example.santiye.databinding.FragmentProductsBinding
import com.example.santiye.main.WarehomeProduct

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentProductsBinding.inflate(inflater,container,false)

        val recyclerView = binding.warehomeProduct
        val list = ArrayList<WarehomeProduct>()
        list.add(WarehomeProduct("tuğla", "150" , "tane"))
        list.add(WarehomeProduct("tuğla", "150" , "tane"))
        list.add(WarehomeProduct("tuğla", "150" , "tane"))
        list.add(WarehomeProduct("tuğla", "150" , "tane"))
        list.add(WarehomeProduct("tuğla", "150" , "tane"))

        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        val customAdapter = WarehomeProductsRecyclerAdapter(list)

        recyclerView.adapter = customAdapter





        return binding.root
    }
}