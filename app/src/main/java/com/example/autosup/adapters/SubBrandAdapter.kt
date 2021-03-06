package com.example.autosup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.R
import com.example.autosup.model.SubBrand
import com.example.autosup.utils.RecyclerUpdater
import kotlinx.android.synthetic.main.item_sub_brand.view.*

class SubBrandAdapter (
    var cars: ArrayList<SubBrand>,
    private val itemClickListener: OnSubBrandItemClickListener
) : RecyclerView.Adapter<SubBrandAdapter.SubBrandViewHolder>(), RecyclerUpdater {

    private val allCars = ArrayList(cars)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubBrandViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_sub_brand, parent, false))

    override fun getItemCount() = cars.size

    override fun updateRecycler(string: String) {
        val updatedCars = allCars.filter { brand -> brand.name.startsWith(string, true) }
        cars.clear()
        cars.addAll(updatedCars)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SubBrandViewHolder, position: Int) {
        cars.let {
            holder.bind(cars[position], itemClickListener)
        }
    }

    class SubBrandViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.item_text
        fun bind(car: SubBrand, clickListener: OnSubBrandItemClickListener) {
            textView.text = car.name
            itemView.setOnClickListener {
                clickListener.onItemClicked(car)
            }
        }
    }
}

interface OnSubBrandItemClickListener {
    fun onItemClicked(subBrand: SubBrand)
}