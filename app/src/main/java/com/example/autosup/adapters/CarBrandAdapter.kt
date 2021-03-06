package com.example.autosup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.autosup.model.CarBrand
import com.example.autosup.R
import com.example.autosup.utils.RecyclerUpdater
import kotlinx.android.synthetic.main.item_car_logo.view.*

class CarBrandAdapter(
    var cars: ArrayList<CarBrand>,
    private val itemClickListener: OnCarItemClickListener
) : RecyclerView.Adapter<CarBrandAdapter.CarViewHolder>(), RecyclerUpdater {

    private val allCars = ArrayList(cars)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CarViewHolder {
        return CarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_car_logo, parent, false)
        )
    }

    override fun updateRecycler(string: String) {
        val updatedCars = allCars.filter { brand -> brand.name.startsWith(string, true) }
        cars.clear()
        cars.addAll(updatedCars)
        notifyDataSetChanged()
    }

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        cars.let {
            holder.bind(cars[position], itemClickListener)
        }
    }

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.carLogoView
        private val textView = view.brand_textview
        fun bind(car: CarBrand, clickListener: OnCarItemClickListener) {
            textView.text = car.name
            loadImage(car.imageUrl, imageView)
            itemView.setOnClickListener {
                clickListener.onItemClicked(car)
            }
        }

        private fun loadImage(url: String, imageView: ImageView) {
            Glide.with(imageView)  //2
                .load("https://autosup.by/$url") //3
                .centerCrop()
                .override(100, 100)
                .into(imageView)
        }
    }
}

interface OnCarItemClickListener {
    fun onItemClicked(car: CarBrand)
}
