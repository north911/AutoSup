package com.example.autosup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.autosup.R
import com.example.autosup.model.Part
import com.example.autosup.utils.RecyclerUpdater
import kotlinx.android.synthetic.main.item_sub_brand.view.*

class PartAdapter(
    var parts: ArrayList<Part>,
    private val itemClickListener: OnPartClickListener
) : RecyclerView.Adapter<PartAdapter.PartViewHolder>(), RecyclerUpdater {

    private val allCarParts = ArrayList(parts)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PartViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sub_brand, parent, false)
        )

    override fun updateRecycler(string: String) {
        val updatedCars = allCarParts.filter { brand -> brand.name.startsWith(string, true) }
        parts.clear()
        parts.addAll(updatedCars)
        notifyDataSetChanged()
    }

    override fun getItemCount() = parts.size

    override fun onBindViewHolder(holder: PartViewHolder, position: Int) {
        parts.let {
            holder.bind(parts[position], itemClickListener)
        }
    }

    class PartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.item_text
        fun bind(part: Part, clickListener: OnPartClickListener) {
            textView.text = part.name
            itemView.setOnClickListener {
                clickListener.onItemClicked(part)
            }
        }
    }
}

interface OnPartClickListener {
    fun onItemClicked(part: Part)
}