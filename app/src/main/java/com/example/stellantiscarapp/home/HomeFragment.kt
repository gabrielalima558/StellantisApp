package com.example.stellantiscarapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.stellantiscarapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        goToCarsList()
        goToChargingStation()
        return binding.root
    }

    private fun goToCarsList() {
        binding.myCars.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToListCarsFragment())
        }
    }

    private fun goToChargingStation() {
        binding.carCharger.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToListChargerFragment())
        }
    }
}