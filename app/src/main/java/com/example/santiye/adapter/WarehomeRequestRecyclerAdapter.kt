package com.example.santiye.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.product.RequestL
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WarehomeRequestRecyclerAdapter (val requestList: ArrayList<RequestL>) : RecyclerView.Adapter<WarehomeRequestRecyclerAdapter.RequestViewHolder>() {

    private lateinit var firestore: FirebaseFirestore






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_warehome_request,parent,false)
        firestore = Firebase.firestore
        return RequestViewHolder(view,parent)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bindItem(requestList[position])

        holder.noButton.setOnClickListener(View.OnClickListener {
            val collection = firestore.collection("Request")
            collection.document(requestList[position].id).update("confirmation", "true")
        })

        holder.okButton.setOnClickListener(View.OnClickListener {
            val  collection = firestore.collection("Request")
            collection.document(requestList[position].id).update("confirmation", "false")
        })
    }

    override fun getItemCount(): Int {
       return requestList.size
    }

    class RequestViewHolder(view: View, parentt: ViewGroup) : RecyclerView.ViewHolder(view){
        val parent = parentt
        val textBlok = view.findViewById<TextView>(R.id.list_item_warehome_request_blok)
        val textFloor = view.findViewById<TextView>(R.id.list_item_warehome_request_floor)
        val textName = view.findViewById<TextView>(R.id.list_item_warehome_request_product)
        val textQentity = view.findViewById<TextView>(R.id.list_item_warehome_request_quantity)
        val noButton = view.findViewById<Button>(R.id.list_item_warehome_button_no)
        val okButton = view.findViewById<Button>(R.id.list_item_warehome_button_ok)

        fun bindItem(requestModel: RequestL){
            textBlok.text = requestModel.blok
            textFloor.text = requestModel.floor
            textName.text = requestModel.meterial
            textQentity.text = requestModel.quentity

            if (requestModel.confirmation == "true"){
                noButton.isVisible = false
                okButton.isVisible = false

            }else if (requestModel.confirmation == "false"){
                noButton.isVisible = false
                okButton.isVisible = false
            }
        }
    }
}
