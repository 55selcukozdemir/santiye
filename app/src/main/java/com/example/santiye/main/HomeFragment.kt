package com.example.santiye.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.adapter.MainHomeRecyclerAdapter
import com.example.santiye.databinding.FragmentMainHomeBinding
import com.example.santiye.product.Content
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentMainHomeBinding


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentMainHomeBinding.inflate(inflater, container, false)

        val recyclerView = binding.mainHomeRecylerView
        dialogSheet(binding,inflater)
        recyclerView.layoutManager = LinearLayoutManager(inflater.context,LinearLayoutManager.VERTICAL,false)


        val contentList = ArrayList<Content>()
        contentList.add(Content("selçuk", "özdemir", "B", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("muhammet", "özdemir", "B", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("murat", "özdemir", "B", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("samet", "özdemir", "A", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("selçuk", "özdemir", "A", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))
        contentList.add(Content("selçuk", "özdemir", "A", "bina temeli atılmaktadır",R.drawable.`in`, R.drawable.`ins`,"dsf"))


        val mainList = contentListDesign(contentList,"B")


        val adapter = MainHomeRecyclerAdapter(mainList)

        recyclerView.adapter= adapter


        return binding.root

    }

    //Bottom sheet işlemleri burada
    fun dialogSheet(binding: FragmentMainHomeBinding, inflater: LayoutInflater){
        binding.mainHomeButton.setOnClickListener(View.OnClickListener {
            val dialog = BottomSheetDialog(inflater.context)

            val inflate = layoutInflater.inflate(R.layout.bottom_sheet_main_home,null)
            val buttonDisms =  inflate.findViewById<Button>(R.id.button_dismis)

            buttonDisms.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })


            dialog.setCancelable(false)
            dialog.setContentView(inflate)
            dialog.show()

        })
    }


    // Ana sayfa da görünecek olan içeriklerin sıralanmsı.
    fun contentListDesign(list: ArrayList<Content>, item: String): ArrayList<Content>{


        val listt = ArrayList<Content>()

        for (x in list){
            if (x.name == item || x.location == item){
                listt.add(x)
            }
        }

        return listt
    }



}