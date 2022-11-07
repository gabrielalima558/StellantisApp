package com.example.stellantiscarapp.chargingstation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.stellantiscarapp.api.model.ChargingStation

class ChargingStationDiffCallback(
    private val oldList: List<ChargingStation>,
    private val newList: List<ChargingStation>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}