package com.example.santiye.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.santiye.R
import com.example.santiye.adapter.CustomCheackBox
import com.example.santiye.adapter.MainHomeRecyclerAdapter
import com.example.santiye.databinding.FragmentMainHomeBinding
import com.example.santiye.product.Content
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentMainHomeBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var contentList: ArrayList<Content>


    private lateinit var adapter: MainHomeRecyclerAdapter
    private val TAG = "HomeFragment"

    private lateinit var buttonAdd: Button
    private lateinit var layout: LinearLayout
    private lateinit var spinnerList: ArrayList<String>

    private lateinit var customCheackBox: CustomCheackBox


    private lateinit var view: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainHomeBinding.inflate(inflater, container, false)
        firestore = Firebase.firestore

        val recyclerView = binding.mainHomeRecylerView
        dialogSheet(binding, inflater)
        recyclerView.layoutManager =
            LinearLayoutManager(inflater.context, LinearLayoutManager.VERTICAL, false)

        Toast.makeText(context, "çalışıyor", Toast.LENGTH_LONG).show()

        spinnerList = ArrayList()
        contentList = ArrayList<Content>()

        postGet()
        customCheackBox = CustomCheackBox(inflater.context);


//        val  list = contentListDesign(contentList, "duvar")


        adapter = MainHomeRecyclerAdapter(contentList)
        recyclerView.adapter = adapter
        return binding.root


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.bottom_nav_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }


    //Bottom sheet işlemleri burada
    fun dialogSheet(binding: FragmentMainHomeBinding, inflater: LayoutInflater) {
        val fab: View = binding.floatingActionButton3

        fab.setOnClickListener {

            val dialog = BottomSheetDialog(inflater.context)
            val inflate = layoutInflater.inflate(R.layout.bottom_sheet_main_home, null)
            val buttonDisms = inflate.findViewById<Button>(R.id.button_dismis)
            layout = inflate.findViewById(R.id.linearLayout)



            view = customCheackBox.getView()
            layout.addView(view)


            buttonAdd = inflate.findViewById<Button>(R.id.addedButton2)
            buttonAdd.setOnClickListener(View.OnClickListener {
                for (i in customCheackBox.getSpinner()) {
                    spinnerList.add(i.selectedItem as String)
                }
            })


            buttonDisms.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })


            dialog.setCancelable(false)
            dialog.setContentView(inflate)
            dialog.show()

        }
    }


    // Ana sayfa da görünecek olan içeriklerin sıralanmsı.
    fun contentListDesign(
        list: ArrayList<Content>,
//        floor: String,
//        block: String,
//        ticket: String
    ): ArrayList<Content> {


        val baslıklar = customCheackBox.getDocument()

        val listContent1 = ArrayList<Content>()
        for (t in baslıklar) {
            for (liste in list) {
                if (t == liste.contentImage) {
                    listContent1.add(liste)
                }
            }
        }


        // ArrayList her elemenı için değrlendirme yapılıyor
        val list1 = ArrayList<Content>()

        for (x in list) {
//            if (x.name.lowercase().contains(item)) {
//                list1.add(x)
//            }
        }


        val list2 = ArrayList<Content>()
        for (x in list1) {
//          /  if(x.)
        }


        return list1
    }

    private fun postGet() {
        contentList.clear()
        firestore.collection("posts").orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
                } else {

                    if (value != null) {
                        if (!value.isEmpty) {
                            val document = value.documents
                            for (d in document) {
                                val email = d.get("email") as String
                                val floor = d.get("floor") as String
                                val block = d.get("block") as String
                                val contentImage = d.get("contentImage") as String
                                val explanation = d.get("explanation") as String
                                val ticket = d.get("ticket") as String

                                //zamanı ekrana yazırmak için yapılan işlemeler
                                val timestamp = d["date"] as com.google.firebase.Timestamp
                                val milliseconds =
                                    timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000
                                val sdf = SimpleDateFormat("MM/dd/yyyy")
                                val netDate = Date(milliseconds)
                                val date = sdf.format(netDate).toString()

                                val content = Content(
                                    email,
                                    floor,
                                    block,
                                    contentImage,
                                    explanation,
                                    ticket,
                                    date
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