package com.example.santiye.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        val meterial  = view.findViewById<TextView>(R.id.list_item_main_products_meterial)
        val location = view.findViewById<TextView>(R.id.list_item_main_products_location)
        val confirmationImage = view.findViewById<ImageView>(R.id.image_view)
        val request = view.findViewById<TextView>(R.id.list_item_main_products_request_unit)

        fun bindItem(contentModel: Meterial){
            meterial.text = contentModel.meterial
            location.text = (contentModel.blok  + "/" + contentModel.floor)
            request.text = contentModel.quentity


            if (contentModel.confirmation == "true"){
                confirmationImage.setImageResource(R.drawable.check)
            }else if(contentModel.confirmation == "false"){
                confirmationImage.setImageResource(R.drawable.cancel)
            }else{
                confirmationImage.setImageResource(R.drawable.search)
            }
        }
    }
}