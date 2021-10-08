package com.example.santiye.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.adapter.MainHomeRecyclerAdapter
import com.example.santiye.databinding.FragmentMainHomeBinding
import com.example.santiye.product.Content

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentMainHomeBinding


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentMainHomeBinding.inflate(inflater, container, false)

        val recyclerView = binding.mainHomeRecylerView

        val contentList = ArrayList<Content>()
        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))

        recyclerView.layoutManager = LinearLayoutManager(inflater.context,LinearLayoutManager.VERTICAL,false)

        val adapter = MainHomeRecyclerAdapter(contentList)

        recyclerView.adapter= adapter


        return binding.root

    }
}