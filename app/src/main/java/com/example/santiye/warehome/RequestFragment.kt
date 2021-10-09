package com.example.santiye.warehome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.adapter.WarehomeRequestRecyclerAdapter
import com.example.santiye.databinding.FragmentRequestBinding
import com.example.santiye.product.RequestL

class RequestFragment : Fragment() {

    private lateinit var binding: FragmentRequestBinding


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRequestBinding.inflate(inflater,container,false)

        val recyclerView = binding.warehomeRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        val requestList = ArrayList<RequestL>()
        requestList.add(RequestL("B", "2" , "tuğla", "150"))
        requestList.add(RequestL("B", "2" , "tuğla", "150"))
        requestList.add(RequestL("B", "2" , "tuğla", "150"))
        requestList.add(RequestL("B", "2" , "tuğla", "150"))
        requestList.add(RequestL("B", "2" , "tuğla", "150"))
        requestList.add(RequestL("B", "2" , "tuğla", "150"))
        requestList.add(RequestL("B", "2" , "tuğla", "150"))
        requestList.add(RequestL("B", "2" , "tuğla", "150"))
        requestList.add(RequestL("B", "2" , "tuğla", "150"))

        val customAdapter = WarehomeRequestRecyclerAdapter(requestList)
        recyclerView.adapter = customAdapter



        return binding.root

    }
}