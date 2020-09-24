package com.example.autosup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.R
import com.example.autosup.model.CarPart
import kotlinx.android.synthetic.main.item_sub_brand.view.*

class CarPartAdapter (
    var carParts: ArrayList<CarPart>,
    private val itemClickListener: OnCarPartClickListener
) : RecyclerView.Adapter<CarPartAdapter.CarPartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarPartViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_sub_brand, parent, false))

    override fun getItemCount() = carParts.size

    override fun onBindViewHolder(holder: CarPartViewHolder, position: Int) {
        carParts.let {
            holder.bind(carParts[position], itemClickListener)
        }
    }

    class CarPartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.item_text
        fun bind(carPart: CarPart, clickListener: OnCarPartClickListener) {
            textView.text = carPart.name
            itemView.setOnClickListener {
                clickListener.onItemClicked(carPart)
            }
        }
    }
}

interface OnCarPartClickListener {
    fun onItemClicked(car: CarPart)
}