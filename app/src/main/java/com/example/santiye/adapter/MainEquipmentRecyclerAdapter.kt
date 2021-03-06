package com.example.santiye.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.main.EquipmentPActivity
import com.example.santiye.product.Equipment
import com.squareup.picasso.Picasso

class MainEquipmentRecyclerAdapter(val equipmentList: ArrayList<Equipment>, val context: Context): RecyclerView.Adapter<MainEquipmentRecyclerAdapter.EquipmentViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_equipment,parent,false)
        return EquipmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        holder.bintItem(equipmentList[position])

        holder.layout.setOnClickListener(View.OnClickListener {
            val i = Intent(context, EquipmentPActivity::class.java)
            i.putExtra("machineName", equipmentList[position].name)
            context.startActivity(i)
        })
    }

    override fun getItemCount(): Int {
        return equipmentList.size
    }

    class EquipmentViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.list_item_equipment_image_view)
        val textName = view.findViewById<TextView>(R.id.list_item_equipment_text_view_date1)
        val layout = view.findViewById<CardView>(R.id.list_item_equipment_card_view)

        fun bintItem(equipmentModel: Equipment){
            Picasso.get().load(equipmentModel.image).into(imageView)
            textName.text = equipmentModel.name
        }


    }
}
