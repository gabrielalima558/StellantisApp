package com.example.stellantiscarapp.newcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stellantiscarapp.database.Car
import com.example.stellantiscarapp.database.CarDatabase
import com.example.stellantiscarapp.databinding.FragmentNewCarBinding

class NewCarFragment : Fragment() {
    private lateinit var newCarViewModel: NewCarViewModel
    private var _binding: FragmentNewCarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewCarBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = CarDatabase.getInstace(application).carDao
        val viewModelFactory = NewCarViewModelFactory(dataSource)

        newCarViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewCarViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveCarButton.setOnClickListener {
            addNewCar()
        }
    }

    private fun addNewCar() {
        val carName = binding.carNameEdit.text.toString()
        val manufacturerName = binding.manufacturerNameEdit.text.toString()
        val lastMaintenance = binding.lastMaintenanceEdit.text.toString()
        if (carName.isEmpty()) {
            Toast.makeText(context, "Nome do carro não está preenchido!", Toast.LENGTH_LONG).show()
        } else if (manufacturerName.isEmpty()) {
            Toast.makeText(context, "Nome do fabricante não está preenchido!", Toast.LENGTH_LONG)
                .show()
        } else if (lastMaintenance.isEmpty()) {
            Toast.makeText(
                context,
                "Data da última mautenção não está preenchido!",
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            insertCar(
                Car(
                    carName = carName,
                    manufacturerName = manufacturerName,
                    lastMaintenance = lastMaintenance
                )
            )
        }
    }

    private fun insertCar (car: Car) {
        newCarViewModel.startInsertCar(car)
        findNavController().navigate(NewCarFragmentDirections.actionNewCarFragmentToListCarsFragment())
    }

}