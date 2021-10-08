package com.example.santiye.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.product.Equipment

class MainEquipmentRecyclerAdapter(val equipmentList: ArrayList<Equipment>): RecyclerView.Adapter<MainEquipmentRecyclerAdapter.EquipmentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_equipment,parent,false)
        return EquipmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        holder.bintItem(equipmentList[position])
    }

    override fun getItemCount(): Int {
        return equipmentList.size
    }

    class EquipmentViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.list_item_equipment_image_view)
        val textDate1 = view.findViewById<TextView>(R.id.list_item_equipment_text_view_date1)
        val textDate2 = view.findViewById<TextView>(R.id.list_item_equipment_text_view_date2)

        fun bintItem(equipmentModel: Equipment){
            imageView.setImageResource(equipmentModel.image)
            textDate1.text = equipmentModel.data1
            textDate2.text = equipmentModel.data2
        }
    }
}
