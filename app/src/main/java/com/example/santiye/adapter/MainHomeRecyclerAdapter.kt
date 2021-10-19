package com.example.santiye.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.adapter.MainHomeRecyclerAdapter.*
import com.example.santiye.product.Content
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class MainHomeRecyclerAdapter(val contentList: ArrayList<Content>) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_home,parent,false)
        firestore = Firebase.firestore
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(contentList[position])
        Picasso.get().load(contentList.get(position).contentImage).into(holder.contentImage)

        firestore.collection("users").get().addOnSuccessListener {

            for (users in it){
                if (contentList.get(position).email == users.get("email") as String ){

                    holder.name.text = (users.get("name").toString() + " " + users.get("lastName").toString())
                    Picasso.get().load(users.get("userImage") as String).into(holder.personImage)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.main_home_text_view_name)
        val location: TextView = view.findViewById(R.id.main_home_text_view_location)
        val ticket: TextView = view.findViewById(R.id.main_home_text_view_ticket)
        val explanation: TextView = view.findViewById(R.id.main_home_text_view_explanation)
        val personImage: ImageView = view.findViewById(R.id.main_home_image_view_person)
        val contentImage: ImageView = view.findViewById(R.id.main_home_image_view_content)

        fun bindItem(contentModel: Content){
            location.text = (contentModel.block + " " + contentModel.floor + " " + contentModel.date)
            ticket.text = contentModel.ticket
            explanation.text = contentModel.explanation
        }

    }
}
