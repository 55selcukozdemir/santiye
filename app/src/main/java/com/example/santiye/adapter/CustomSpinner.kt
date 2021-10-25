package com.example.santiye.adapter

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.*
import com.example.santiye.R
import com.google.common.math.LinearTransformation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CustomSpinner {
    private var context:Context
    private var firestore: FirebaseFirestore
    private var view: LinearLayout
    private var spinnerList: ArrayList<Spinner>
    private var categoryTagName: ArrayList<String>


    constructor(context: Context){
        firestore = Firebase.firestore
        this.context = context
        view = LinearLayout(context)
        view.orientation = LinearLayout.VERTICAL
        spinnerList = ArrayList()
        categoryTagName = ArrayList()

        getCategory()
    }



    private fun getCategory(){

        firestore.collection("category").get().addOnSuccessListener { result ->

            for (document in result){


                // LinearLayout oluşturlması
                var layout = LinearLayout(context)
                layout.orientation = LinearLayout.VERTICAL
                val drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.layout_background, null)
                val par = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                par.setMargins(10,10,10,10)
                layout.background = drawable
                // LinearLayout oluşturlması (son)



                // TextView oluşturulması
                val textView = TextView(context)
                textView.gravity = 11
                textView.text = document.id
                //TextView oluşturulması (son)


                layout.addView(textView)


                categoryTagName.add(document.id)

                val list = ArrayList<String>()
                list.add("Seçim yap")

                for(i in document.data.size downTo 1){
                    list.add(document.get(i.toString()) as String)
                }


                //Spinner oluşturulması
                val spinner = Spinner(context)
                val floorAdapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,list)
                spinner.adapter = floorAdapter
                //Spinner oluşturulması (son)

                spinnerList.add(spinner)
                layout.addView(spinner)
                view.addView(layout , par)
            }
        }
    }

    fun getView(): LinearLayout{
        return this.view
    }
    fun getSpinner(): ArrayList<Spinner>{
        return this.spinnerList
    }
}