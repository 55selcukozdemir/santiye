package com.example.santiye.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.santiye.R
import com.example.santiye.main.WarehomeProduct

class WarehomeProductsRecyclerAdapter(val productList: ArrayList<WarehomeProduct>): RecyclerView.Adapter<WarehomeProductsRecyclerAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_warehome_products,parent,false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bintItem(productList[position])

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val textname = view.findViewById<TextView>(R.id.list_item_warehome_products_meterial)
        val textquentity = view.findViewById<TextView>(R.id.list_item_warehome_products_floor)
        val textUnit = view.findViewById<TextView>(R.id.list_item_warehome_products_block)

        fun bintItem(productModel: WarehomeProduct){
            textUnit.text = productModel.name
            textUnit.text = productModel.unit
            textquentity.text = productModel.quentity
        }

    }
}
