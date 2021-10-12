package com.example.santiye.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.R
import com.example.santiye.adapter.MainHomeRecyclerAdapter
import com.example.santiye.databinding.FragmentMainProfileBinding
import com.example.santiye.product.Content

class Profile : Fragment() {

    private lateinit var binding: FragmentMainProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentMainProfileBinding.inflate(layoutInflater)

        val recyclerView = binding.fragmentMainProfileRecyclerView

        val contentList = ArrayList<Content>()
//        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
//        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
//        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
//        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
//        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
//        contentList.add(Content("selçuk", "özdemir", "B-3", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))

        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val adapter = MainHomeRecyclerAdapter(contentList)
        recyclerView.adapter = adapter

        binding.button3.setOnClickListener(View.OnClickListener {
            val i = Intent(activity,ProductAdded::class.java)
            startActivity(i)
        })

        return binding.root



    }

}