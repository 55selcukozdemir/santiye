package com.example.santiye.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.adapter.MainHomeRecyclerAdapter.*
import com.example.santiye.product.Content
import com.squareup.picasso.Picasso

class MainHomeRecyclerAdapter(val contentList: ArrayList<Content>) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_home,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(contentList[position])
        Picasso.get().load(contentList.get(position).contentImage).into(holder.imageViewContent)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val textViewName: TextView = view.findViewById(R.id.main_home_text_view_name)
        val textViewLocation: TextView = view.findViewById(R.id.main_home_text_view_location)
        val textViewTicket: TextView = view.findViewById(R.id.main_home_text_view_ticket)
        val textViewExplanation: TextView = view.findViewById(R.id.main_home_text_view_explanation)
        val imageViewPerson: ImageView = view.findViewById(R.id.main_home_image_view_person)
        val imageViewContent: ImageView = view.findViewById(R.id.main_home_image_view_content)

        fun bindItem(contentModel: Content){
            textViewName.text = contentModel.name
            textViewLocation.text = contentModel.location
            textViewTicket.text = contentModel. ticket
            textViewExplanation.text = contentModel.explanation
            imageViewPerson.setImageResource(contentModel.personImage)

        }

    }
}
