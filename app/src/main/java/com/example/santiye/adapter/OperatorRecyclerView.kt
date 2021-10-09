package com.example.santiye.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.product.OperatorDuty

class OperatorRecyclerView(val duty: ArrayList<OperatorDuty>, val context: Context): RecyclerView.Adapter<OperatorRecyclerView.HolderViewOperator>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderViewOperator {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_operator,parent,false)

        return HolderViewOperator(view)

    }

    override fun onBindViewHolder(holder: HolderViewOperator, position: Int) {
        holder.bindItem(duty[position])
    }

    override fun getItemCount(): Int {
        return duty.size
    }

    class HolderViewOperator(view: View) : RecyclerView.ViewHolder(view){

        val textLocation = view.findViewById<TextView>(R.id.list_item_operator_location)
        val textdate1 = view.findViewById<TextView>(R.id.list_item_operator_date1)
        val textdate2 = view.findViewById<TextView>(R.id.list_item_operator_date2)
        val okButton = view.findViewById<Button>(R.id.list_item_operator_button_ok)

        fun bindItem(dutyModel: OperatorDuty){
            textLocation.text = dutyModel.location
            textdate1.text = dutyModel.date1
            textdate2.text = dutyModel.date2
        }

    }


}