package com.example.santiye.warehome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.santiye.databinding.FragmentProductArrangemetBinding

class ProductArrangementFragment : Fragment() {

    private var _binding: FragmentProductArrangemetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProductArrangemetBinding.inflate(inflater,container,false)
        return binding.root
    }
}