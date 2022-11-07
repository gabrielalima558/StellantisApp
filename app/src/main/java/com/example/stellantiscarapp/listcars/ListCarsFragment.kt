package com.example.stellantiscarapp.listcars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stellantiscarapp.database.Car
import com.example.stellantiscarapp.database.CarDatabase
import com.example.stellantiscarapp.databinding.FragmentListCarsBinding
import com.example.stellantiscarapp.listcars.adapter.CarAdapter
import com.example.stellantiscarapp.listcars.adapter.CarViewHolder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListCarsFragment : Fragment() {
    private lateinit var listCarsViewModel: ListCarsViewModel
    private var _binding: FragmentListCarsBinding? = null
    private val binding get() = _binding!!
    private var carJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = CarDatabase.getInstace(application).carDao
        val viewModelFactory = ListCarsViewModelFactory(dataSource)
        _binding = FragmentListCarsBinding.inflate(inflater, container, false)

        listCarsViewModel =
            ViewModelProvider(this, viewModelFactory).get(ListCarsViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getList()
        addCar()
        initSwipeToDelete()
    }
    private fun getList() {
        val adapter = CarAdapter()
        binding.listCars.adapter = adapter
        binding.listCars.layoutManager = LinearLayoutManager(this.activity)

        carJob?.cancel()
        carJob = lifecycleScope.launch {
            listCarsViewModel.list.collectLatest { adapter.submitData(it.map { itens -> itens }) }

        }

        adapter.setOnItemClickListener(object : CarAdapter.OnItemClickListener {
            override fun OnItemclick(position: Int, car: Car?) {
                findNavController().navigate(
                    ListCarsFragmentDirections.actionListCarsFragmentToDetailCarFragment(
                        carName = car?.carName
                    )
                )
            }
        })
    }

    private fun addCar(){
        binding.fab.setOnClickListener {
            findNavController().navigate(ListCarsFragmentDirections.actionListCarsFragmentToNewCarFragment())
        }
    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val carViewHolder = viewHolder as CarViewHolder
                return if (carViewHolder.car != null) {
                    makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                } else {
                    makeMovementFlags(0, 0)
                }
            }

            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as CarViewHolder).car?.let {
                    listCarsViewModel.startDeleteCar(it)
                }
            }
        }).attachToRecyclerView(binding.listCars)
    }
}