package com.example.santiye.warehome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.adapter.WarehomeProductsRecyclerAdapter
import com.example.santiye.databinding.FragmentProductArrangemetBinding
import com.example.santiye.databinding.FragmentProductsBinding
import com.example.santiye.main.WarehomeProduct
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var postArrayList: ArrayList<WarehomeProduct>
    private lateinit var customAdapter: WarehomeProductsRecyclerAdapter



    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentProductsBinding.inflate(inflater,container,false)
        db = Firebase.firestore
        val recyclerView = binding.warehomeProduct
        postArrayList = ArrayList<WarehomeProduct>()


        getPorudct()

        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        customAdapter = WarehomeProductsRecyclerAdapter(postArrayList)

        recyclerView.adapter = customAdapter





        return binding.root
    }

    fun getPorudct () {


        db.collection("product").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener { value, error ->

            postArrayList.clear()
            if(error != null){
                Toast.makeText(context,error.localizedMessage, Toast.LENGTH_LONG).show()

            }
            else{
                if (value != null){
                    if (!value.isEmpty){
                        val documents = value.documents

                        for (document in documents){
                            val name = document.get("name") as String
                            val size = document.get("size") as String
                            val unit = document.get("unit") as String
                            val post = WarehomeProduct(name,size,unit)
                            postArrayList.add(post)


                        }

                        customAdapter!!.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}