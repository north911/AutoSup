package com.example.autosup.Adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.autosup.Model.CarBrand
import com.example.autosup.R
import kotlinx.android.synthetic.main.item_car_logo.view.*

class CarBrandAdapter(
    var cars: ArrayList<CarBrand>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CarBrandAdapter.CarViewHolder>() {

    fun updateUsers(newUsers: List<CarBrand>) {
        cars.clear()
        cars.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CarViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_car_logo, parent, false)
    )

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        cars.let {
            holder.bind(cars[position], itemClickListener)
        }
    }

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.carLogoView
        private val textView = view.brand_textview
        fun bind(car: CarBrand, clickListener: OnItemClickListener) {
            textView.text = car.name
            loadImage(car.url, imageView)
            itemView.setOnClickListener {
                clickListener.onItemClicked(car)
            }
        }

        fun loadImage(url: String, imageView: ImageView) {
            Glide.with(imageView)  //2
                .load("https://autosup.by/$url") //3
                .centerCrop() //4
                .into(imageView)
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(car: CarBrand)
}
