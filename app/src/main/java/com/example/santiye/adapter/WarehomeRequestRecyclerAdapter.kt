package com.example.santiye.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.product.RequestL
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WarehomeRequestRecyclerAdapter (val requestList: ArrayList<RequestL>) : RecyclerView.Adapter<WarehomeRequestRecyclerAdapter.RequestViewHolder>() {

    private lateinit var firestore: FirebaseFirestore
    private  val TAG = "WarehomeRequestRecycler"






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_warehome_request,parent,false)
        firestore = Firebase.firestore
        return RequestViewHolder(view,parent)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bindItem(requestList[position])

        holder.okButton.setOnClickListener(View.OnClickListener {
            val collection = firestore.collection("request")

            collection.document(requestList[position].id).update("confirmation", "true").addOnSuccessListener {


                firestore.collection("product").whereEqualTo("name", requestList.get(position).material).get().addOnSuccessListener {
                    for (i in it){
                        val oldSize = (i.get("size") as String).toInt()
                        val requestSize = requestList.get(position).quentity.toInt()

                        Log.d(TAG, "onBindViewHolder: ${oldSize}   / $requestSize   ${oldSize > requestSize}")

                        if (oldSize > requestSize){
                            Log.d(TAG, "onBindViewHolder: ${oldSize} $requestSize")
                            firestore.collection("product").document(i.id).update("size",(oldSize-requestSize).toString())
                        }else{
                            Log.d(TAG, "else çalışıyor")
                            Toast.makeText(holder.parent.context, "İstenilen miktar depoda bulunmamaktadır.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })

        holder.noButton.setOnClickListener(View.OnClickListener {
            val  collection = firestore.collection("request")
            collection.document(requestList[position].id).update("confirmation", "false").addOnSuccessListener {

            }

        })
    }

    override fun getItemCount(): Int {
       return requestList.size
    }

    class RequestViewHolder(view: View, parentt: ViewGroup) : RecyclerView.ViewHolder(view){
        val parent = parentt

        val quentity = view.findViewById<TextView>(R.id.list_item_warehome_request_quentity)
        val location = view.findViewById<TextView>(R.id.list_item_warehome_request_location)
        val product = view.findViewById<TextView>(R.id.list_item_warehome_request_product)
        val noButton = view.findViewById<Button>(R.id.list_item_warehome_button_no)
        val okButton = view.findViewById<Button>(R.id.list_item_warehome_button_ok)
        val layout = view.findViewById<LinearLayout>(R.id.button_layout)

        fun bindItem(requestModel: RequestL){
            quentity.text = requestModel.quentity
            location.text = (requestModel.blok + "/" + requestModel.floor)
            product.text = requestModel.material

            if (requestModel.confirmation == "true" || requestModel.confirmation == "false"){
                layout.visibility = LinearLayout.INVISIBLE
            }

//            if (requestModel.confirmation == "true"){
//                noButton.isVisible = false
//                okButton.isVisible = false
//
//            }else if (requestModel.confirmation == "false"){
//                noButton.isVisible = false
//                okButton.isVisible = false
//            }
        }
    }
}
