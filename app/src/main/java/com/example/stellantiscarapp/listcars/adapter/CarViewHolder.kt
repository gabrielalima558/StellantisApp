package com.example.stellantiscarapp.listcars.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stellantiscarapp.R
import com.example.stellantiscarapp.database.Car


class CarViewHolder(parent: ViewGroup, listener: CarAdapter.OnItemClickListener) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    ) {
    var car: Car? = null
        private set
    private val nameCar = itemView.findViewById<TextView>(R.id.name_car_txt)
    private val lastMaintenance = itemView.findViewById<TextView>(R.id.last_maintenance_txt)

    fun bindTo(item: CarListItem?) {
        if (item is CarListItem.Separator) {
            nameCar.text = item.name
            lastMaintenance.text = item.lastMaintenance
            nameCar.setTypeface(null, Typeface.BOLD)
            lastMaintenance.setTypeface(null, Typeface.BOLD)
        } else {
            nameCar.text = item?.name
            lastMaintenance.text = item?.lastMaintenance
            nameCar.setTypeface(null, Typeface.NORMAL)
            lastMaintenance.setTypeface(null, Typeface.NORMAL)
        }
        car = (item as? CarListItem.Item)?.car
        nameCar.text = item?.name
        lastMaintenance.text = item?.lastMaintenance
    }

    init {
        itemView.setOnClickListener {
            listener.OnItemclick(bindingAdapterPosition, car)
        }
    }
}