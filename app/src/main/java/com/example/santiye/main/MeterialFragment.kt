package com.example.santiye.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.adapter.MainMeterialRecyclerAdapter
import com.example.santiye.databinding.FragmentMainMeterialBinding
import com.example.santiye.product.Meterial

class MeterialFragment : Fragment() {

    private lateinit var binding: FragmentMainMeterialBinding


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainMeterialBinding.inflate(inflater, container, false)

        val recyclerView1 =  binding.fragmentMainMeterialRecyclerView

        val contentMeterial = ArrayList<Meterial>()
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))
        contentMeterial.add(Meterial("adan", "mekan", "sayi"))

        recyclerView1.layoutManager = LinearLayoutManager(inflater.context, LinearLayoutManager.VERTICAL,false)

        val adapter1 = MainMeterialRecyclerAdapter(contentMeterial)
        recyclerView1.adapter = adapter1

        return binding.root


    }
}