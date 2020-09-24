package com.example.autosup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.model.Engine
import com.example.autosup.R
import kotlinx.android.synthetic.main.item_sub_brand.view.*

class EngineAdapter (
    var engines: ArrayList<Engine>,
    private val itemClickListener: OnEngineClickListener
) : RecyclerView.Adapter<EngineAdapter.EngineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EngineViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_sub_brand, parent, false))

    override fun getItemCount() = engines.size

    override fun onBindViewHolder(holder: EngineViewHolder, position: Int) {
        engines.let {
            holder.bind(engines[position], itemClickListener)
        }
    }

    class EngineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.item_text
        fun bind(car: Engine, clickListener: OnEngineClickListener) {
            textView.text = car.name + car.carBack + car.fuel
            itemView.setOnClickListener {
                clickListener.onItemClicked(car)
            }
        }
    }
}

interface OnEngineClickListener {
    fun onItemClicked(car: Engine)
}