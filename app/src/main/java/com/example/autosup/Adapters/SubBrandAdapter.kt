package com.example.autosup.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.Model.SubBrand
import com.example.autosup.R
import kotlinx.android.synthetic.main.item_sub_brand.view.*

class SubBrandAdapter (
    var cars: ArrayList<SubBrand>,
    private val itemClickListener: OnSubBrandItemClickListener
) : RecyclerView.Adapter<SubBrandAdapter.SubBrandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubBrandViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_sub_brand, parent, false))

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: SubBrandViewHolder, position: Int) {
        cars.let {
            holder.bind(cars[position], itemClickListener)
        }
    }

    class SubBrandViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.subBrand_text
        fun bind(car: SubBrand, clickListener: OnSubBrandItemClickListener) {
            textView.text = "asdasdasd"
            itemView.setOnClickListener {
                clickListener.onItemClicked(car)
            }
        }

    }
}

interface OnSubBrandItemClickListener {
    fun onItemClicked(car: SubBrand)
}