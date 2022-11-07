package com.example.stellantiscarapp.chargingstation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stellantiscarapp.chargingstation.adapter.ChargerStationAdapter
import com.example.stellantiscarapp.databinding.FragmentListChargerBinding

class ChargingStationListFragment : Fragment() {
    private val chargingStationViewModel: ChargingStationViewModel by lazy {
        ViewModelProvider(
            this,
            ChargingStationViewModelFactory
        ).get(
            ChargingStationViewModel::class.java
        )
    }
    private var _binding: FragmentListChargerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListChargerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getChargingStationList()
    }

    private fun getChargingStationList() {
        val adapter = ChargerStationAdapter()
        binding.listChargingStation.adapter = adapter
        binding.listChargingStation.layoutManager = LinearLayoutManager(this.activity)
        chargingStationViewModel.getChargingStation()
        chargingStationViewModel.chargingStation.observe(viewLifecycleOwner) {
            adapter.items = it
            binding.loading.visibility = View.GONE
        }

    }

}