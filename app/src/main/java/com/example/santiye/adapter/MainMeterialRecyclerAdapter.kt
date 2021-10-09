package com.example.santiye.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.product.Meterial

class MainMeterialRecyclerAdapter(val contentList: ArrayList<Meterial>): RecyclerView.Adapter<MainMeterialRecyclerAdapter.MeterialViewHoler>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeterialViewHoler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_meterial,parent,false)
        return MeterialViewHoler(view)
    }

    override fun onBindViewHolder(holder: MeterialViewHoler, position: Int) {
        holder.bindItem(contentList[position])
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    class MeterialViewHoler(view: View): RecyclerView.ViewHolder(view){
        val product = view.findViewById<TextView>(R.id.list_item_meterial_textview_product)
        val quantity = view.findViewById<TextView>(R.id.list_item_meterial_textview_quantity)
        val reply = view.findViewById<TextView>(R.id.list_item_meterial_textview_reply)

        fun bindItem(contentModel: Meterial){
           product.text = contentModel.product
           quantity.text = contentModel.quantity
           reply.text  = contentModel.reply
        }
    }
}