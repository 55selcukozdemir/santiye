package com.example.santiye.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.adapter.MainHomeRecyclerAdapter
import com.example.santiye.databinding.FragmentMainHomeBinding
import com.example.santiye.product.Content
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentMainHomeBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var contentList: ArrayList<Content>
    private lateinit var adapter: MainHomeRecyclerAdapter
    private  val TAG = "HomeFragment"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainHomeBinding.inflate(inflater, container, false)
        firestore = Firebase.firestore

        val recyclerView = binding.mainHomeRecylerView
        dialogSheet(binding, inflater)
        recyclerView.layoutManager = LinearLayoutManager(inflater.context, LinearLayoutManager.VERTICAL, false)

        Toast.makeText(context, "çalışıyor", Toast.LENGTH_LONG).show()

        contentList = ArrayList<Content>()


        postGet()

        adapter = MainHomeRecyclerAdapter(contentList)
        recyclerView.adapter = adapter
        return binding.root

    }


    //Bottom sheet işlemleri burada
    fun dialogSheet(binding: FragmentMainHomeBinding, inflater: LayoutInflater) {
        binding.mainHomeButton.setOnClickListener(View.OnClickListener {
            val dialog = BottomSheetDialog(inflater.context)

            val inflate = layoutInflater.inflate(R.layout.bottom_sheet_main_home, null)
            val buttonDisms = inflate.findViewById<Button>(R.id.button_dismis)

            buttonDisms.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })


            dialog.setCancelable(false)
            dialog.setContentView(inflate)
            dialog.show()

        })
    }


    // Ana sayfa da görünecek olan içeriklerin sıralanmsı.
    fun contentListDesign(list: ArrayList<Content>, item: String): ArrayList<Content> {


        val listt = ArrayList<Content>()

        for (x in list) {
            if (x.name == item || x.location == item) {
                listt.add(x)
            }
        }

        return listt
    }

    private fun postGet() {


        contentList.clear()
        firestore.collection("Posts").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
            } else {

                if (value != null) {
                    if (!value.isEmpty) {
                        val document = value.documents

                        for (d in document) {
                            Log.d(TAG, "postGet: ")

                            val comment = d.get("comment") as String
                            val downloadImage = d.get("downloadUrl") as String
                            val content = Content(
                                "boş",
                                "boş",
                                "boş",
                                comment,
                                R.drawable.`in`,
                                downloadImage,
                                "boş"
                            )
                            contentList.add(content)
                        }

                        adapter.notifyDataSetChanged()

                    }
                }
            }

        }


    }


}