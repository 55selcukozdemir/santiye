package com.example.santiye.warehome

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SpinnerAdd {

    private  var firestore: FirebaseFirestore
    private  val TAG = "SpinnerAdd"
    private  var spinnerList : ArrayList<String>
    private  var adapter: ArrayAdapter<String>? = null
    private  var collectionName: String
    private  var inflater: LayoutInflater



    constructor(collectionName: String, inflater: LayoutInflater){
        this.collectionName  = collectionName
        this.inflater = inflater



        firestore = Firebase.firestore


        spinnerList = ArrayList<String>()
        firestore.collection(collectionName).addSnapshotListener{ value, error ->
            if (error != null){
                Toast.makeText(inflater.context, error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (value != null){
                    if (!value.isEmpty){
                        val document = value.documents
                        for (d in document){
                            Log.d(TAG, "spinnerGenerator: spinneradd")
                            spinnerList.add((d.get("name") as String).toString())
                        }

                        adapter = ArrayAdapter(inflater.context, R.layout.simple_spinner_dropdown_item, spinnerList)

                    }
                }
            }
        }
        Log.d(TAG, "spinnerGenerator: girdi")
    }

    fun getAdapter(): ArrayAdapter<String>? {
        return adapter
    }
}