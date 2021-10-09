package com.example.santiye.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.product.RequestL
import org.w3c.dom.Text

class WarehomeRequestRecyclerAdapter (val requestList: ArrayList<RequestL>) : RecyclerView.Adapter<WarehomeRequestRecyclerAdapter.RequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_warehome_request,parent,false)

        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bindItem(requestList[position])
    }

    override fun getItemCount(): Int {
       return requestList.size
    }

    class RequestViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textBlok = view.findViewById<TextView>(R.id.list_item_warehome_request_blok)
        val textFloor = view.findViewById<TextView>(R.id.list_item_warehome_request_floor)
        val textName = view.findViewById<TextView>(R.id.list_item_warehome_request_product)
        val textQentity = view.findViewById<TextView>(R.id.list_item_warehome_request_quantity)

        fun bindItem(requestModel: RequestL){
            textBlok.text = requestModel.blok
            textFloor.text = requestModel.kat
            textName.text = requestModel.name
            textQentity.text = requestModel.quantity
        }
    }
}
