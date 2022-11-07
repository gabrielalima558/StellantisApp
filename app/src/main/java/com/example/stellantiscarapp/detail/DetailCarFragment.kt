package com.example.stellantiscarapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stellantiscarapp.database.Car
import com.example.stellantiscarapp.database.CarDatabase
import com.example.stellantiscarapp.databinding.FragmentDetailCarBinding

class DetailCarFragment : Fragment() {
    private var _binding: FragmentDetailCarBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailCarViewModel: DetailCarViewModel
    private var carName: String? = null
    private var car: Car? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = CarDatabase.getInstace(application).carDao
        val viewModelFactory = DetailCarViewModelFactory(dataSource)
        carName = arguments?.getString("car_name").toString()
        _binding = FragmentDetailCarBinding.inflate(inflater, container, false)
        detailCarViewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailCarViewModel::class.java)
        populateFeedback()
        startFinishChanges()

        return binding.root
    }

    private fun populateFeedback() {
        detailCarViewModel.startGetCar(carName!!)
        detailCarViewModel.car.observe(viewLifecycleOwner) {
            car = it
            populateDetailInfo()
        }
    }

    private fun populateDetailInfo() {
        binding.carNameDetailEdit.setText(car?.carName ?: "")
        binding.lastMaintenanceDetailEdit.setText(car?.lastMaintenance ?: "")
        binding.manufacturerNameDetailEdit.setText(car?.manufacturerName ?: "")
    }

    private fun startFinishChanges() {
        binding.saveCarButtonDetail.setOnClickListener {
            retrieveNewValues()
            detailCarViewModel.startUpdateCar(car!!)
            findNavController().navigate(DetailCarFragmentDirections.actionDetailCarFragmentToListCarsFragment())
        }
    }

    private fun retrieveNewValues() {
        car?.carName = binding.carNameDetailEdit.text.toString()
        car?.lastMaintenance = binding.lastMaintenanceDetailEdit.text.toString()
        car?.manufacturerName = binding.manufacturerNameDetailEdit.text.toString()
    }

}