package com.example.santiye.main

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akexorcist.snaptimepicker.SnapTimePickerDialog
import com.example.santiye.R
import com.example.santiye.adapter.MainEquipmentPRecyclerAdapter
import com.example.santiye.databinding.FragmentEquipmentPBinding
import com.example.santiye.product.EquipmentP
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EquipmentPActivity : AppCompatActivity() {

    private lateinit var binding: FragmentEquipmentPBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var piper: String
    private lateinit var timeList: ArrayList<EquipmentP>
    private lateinit var adapter: MainEquipmentPRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentEquipmentPBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timeList = ArrayList<EquipmentP>()
        firestore = Firebase.firestore
        val recyclerView = binding.fragmentEquipmentPRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        piper = intent.getStringExtra("machineName") as String

        getdate()
        getSpinner()

        adapter = MainEquipmentPRecyclerAdapter(timeList)
        recyclerView.adapter = adapter

        binding.machineAdd.setOnClickListener(View.OnClickListener {
            val date1 = binding.date1.text.toString()
            val date2 = binding.date2.text.toString()
            val spinner1 = binding.spinner1.selectedItem
            val spinner2 = binding.spinner2.selectedItem
           if(date2 != "Bitiş zamanı" || date1 != "Başlangıç zamanı"){
               val map = hashMapOf(
                   "date1" to date1,
                   "date2" to date2,
                   "spinner1" to spinner1,
                   "spinner2" to spinner2,
                   "data" to Timestamp.now()
               )

               firestore.collection(piper).add(map).addOnSuccessListener {
                   Toast.makeText(this, "İstekte bulunuldu.", Toast.LENGTH_SHORT).show()
               }.addOnFailureListener {
                   Toast.makeText(this, "İşlem hatası.", Toast.LENGTH_SHORT).show()
               }

               getdate()
           }else{
               Toast.makeText(this, "Lütfen boş bir zaman dilimi seçiniz.", Toast.LENGTH_SHORT).show()
           }
        })

        binding.date1.setOnClickListener {

            val snapTime = SnapTimePickerDialog.Builder().setTitle(R.string.title_home)
                .setThemeColor(R.color.purple_700)
                .setPrefix(R.string.time_perfix)
                .build()
            snapTime.show(supportFragmentManager, SnapTimePickerDialog.TAG)

            snapTime.setListener { hour, minute ->
                binding.date1.text = "$hour:$minute"
            }
        }

        binding.date2.setOnClickListener {

            val snapTime = SnapTimePickerDialog.Builder().setTitle(R.string.title_home)
                .setThemeColor(R.color.purple_700)
                .setPrefix(R.string.time_perfix)
                .build()
            snapTime.show(supportFragmentManager, SnapTimePickerDialog.TAG)

            snapTime.setListener { hour, minute ->
                binding.date2.text = "$hour:$minute"
            }
        }

    }

    fun getdate() {
        firestore.collection(piper).orderBy("data", Query.Direction.DESCENDING).get().addOnSuccessListener { documents ->
            timeList.clear()
            for (doc in documents) {
                timeList.add(EquipmentP(doc.get("date1") as String, doc.get("date2") as String))
            }

            adapter.notifyDataSetChanged()
        }
    }

    fun getSpinner() {

        val spinner1 = ArrayList<String>()
        firestore.collection( "floor").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {
                        val document = value.documents
                        for (d in document) {
                            spinner1.add(d.get("name") as String)
                        }
                        val spinner1Adapter = ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_spinner_dropdown_item,
                            spinner1
                        )
                        binding.spinner1.adapter = spinner1Adapter
                    }
                }
            }
        }

        val spinner2 = ArrayList<String>()
        firestore.collection("block").addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {
                        val document = value.documents
                        for (d in document) {
                            spinner2.add(d.get("name") as String)
                        }
                        val spinner2Adapter = ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_spinner_dropdown_item,
                            spinner2
                        )
                        binding.spinner2.adapter = spinner2Adapter
                    }
                }
            }
        }
    }
}