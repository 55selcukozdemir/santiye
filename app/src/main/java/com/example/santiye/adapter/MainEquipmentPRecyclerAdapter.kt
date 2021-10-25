package com.example.santiye.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.product.EquipmentP

class MainEquipmentPRecyclerAdapter(val equipmentList: ArrayList<EquipmentP>): RecyclerView.Adapter<MainEquipmentPRecyclerAdapter.MainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_equipment_p,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItem(equipmentList[position])
    }

    override fun getItemCount(): Int {
        return equipmentList.size
    }

    class MainViewHolder (view: View): RecyclerView.ViewHolder(view){
        val textData1 = view.findViewById<TextView>(R.id.list_item_equipment_p_text_view_date1)
        val textData2 = view.findViewById<TextView>(R.id.list_item_equipment_p_text_view_date2)

        fun bindItem(equipmentModel: EquipmentP){
            textData1.text = (equipmentModel.date1 + " / " + equipmentModel.date2)
            textData2.text = equipmentModel.konum
        }
    }


}