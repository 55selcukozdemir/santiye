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
        val meterial  = view.findViewById<TextView>(R.id.list_item_warehome_products_meterial)
        val floor = view.findViewById<TextView>(R.id.list_item_warehome_products_floor)
        val block = view.findViewById<TextView>(R.id.list_item_warehome_products_block)
        val quentity = view.findViewById<TextView>(R.id.list_item_warehome_products_quentity)
        val confirmation = view.findViewById<TextView>(R.id.list_item_warehome_products_confirmation)

        fun bindItem(contentModel: Meterial){
            meterial.text = contentModel.meterial
            floor.text = contentModel.floor
            block.text = contentModel.blok
            quentity.text = contentModel.quentity
            confirmation.text = contentModel.confirmation
        }
    }
}