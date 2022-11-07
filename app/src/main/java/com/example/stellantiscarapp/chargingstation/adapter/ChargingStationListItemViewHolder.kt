package com.example.stellantiscarapp.chargingstation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stellantiscarapp.R
import com.example.stellantiscarapp.api.model.ChargingStation


class ChargingStationListItemViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(items: ChargingStation) {
        itemView.findViewById<TextView>(R.id.name_station).text = items.nome
        itemView.findViewById<TextView>(R.id.address_state).text = items.endereco
        itemView.findViewById<TextView>(R.id.information_state).text = items.informacoes
    }

}