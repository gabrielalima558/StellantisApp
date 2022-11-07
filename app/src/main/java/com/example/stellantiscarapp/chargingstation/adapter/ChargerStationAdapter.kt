package com.example.stellantiscarapp.chargingstation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stellantiscarapp.R
import com.example.stellantiscarapp.api.model.ChargingStation

class ChargerStationAdapter :
    RecyclerView.Adapter<ChargingStationListItemViewHolder>() {

    var items = emptyList<ChargingStation>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ChargingStationDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChargingStationListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_charging, parent, false)
        return ChargingStationListItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ChargingStationListItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun getItemAt(position: Int): ChargingStation {
        return items[position]
    }

    override fun getItemCount(): Int = items.size
}