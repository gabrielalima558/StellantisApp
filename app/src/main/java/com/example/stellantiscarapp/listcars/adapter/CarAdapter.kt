package com.example.stellantiscarapp.listcars.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.stellantiscarapp.database.Car

class CarAdapter : PagingDataAdapter<CarListItem, CarViewHolder>(diffCallback) {
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun OnItemclick(position: Int, car: Car?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(parent, mListener)
    }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<CarListItem>() {
            override fun areItemsTheSame(
                oldItem: CarListItem,
                newItem: CarListItem
            ): Boolean {
                return if (oldItem is CarListItem.Item && newItem is CarListItem.Item) {
                    oldItem.car.id == newItem.car.id
                } else if (oldItem is CarListItem.Separator && newItem is CarListItem.Separator) {
                    oldItem.name == newItem.name
                } else {
                    oldItem == newItem
                }
            }


            override fun areContentsTheSame(
                oldItem: CarListItem,
                newItem: CarListItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}