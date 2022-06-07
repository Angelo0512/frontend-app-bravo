package cr.una.bravo.bravofrontend.view.vehicle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.VehicleCardAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentSearchVehicleBinding
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import cr.una.bravo.bravofrontend.service.VehicleService
import cr.una.bravo.bravofrontend.viewmodel.StateVehicle
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModel
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModelFactory

class SearchVehicleFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentSearchVehicleBinding
    private lateinit var vehicleViewModel: VehicleViewModel
    private val adapter = VehicleCardAdapter()

    private lateinit var viewVehicle: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicleFilter: Spinner
    private lateinit var searcher: SearchView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchVehicleBinding.inflate(inflater, container, false)
        viewVehicle = binding.root

        //Retrofit Service
        val vehicleService = VehicleService.getInstance()
        val vehicleRepository = VehicleRepository(vehicleService)

        //ViewModelfactory
        vehicleViewModel =
                ViewModelProvider(this, VehicleViewModelFactory())[VehicleViewModel::class.java]

        //RecyclerView
        recyclerView = viewVehicle.findViewById(R.id.vehicleList)
        initRecycler()

        //Spinner
        vehicleFilter = viewVehicle.findViewById(R.id.vehicleFilter)
        initSpinner()

        //Search View
        searcher = viewVehicle.findViewById(R.id.vehicleSearch)
        searcher.setOnQueryTextListener(this)

        //Return Button
        viewVehicle.findViewById<ImageButton>(R.id.btn_SearchVeh_Return).setOnClickListener {
            Navigation.findNavController(viewVehicle)
                .navigate(R.id.action_searchVehicleFragment2_to_mainFragment)
        }

        return viewVehicle
    }

    private fun initRecycler() {

        binding.vehicleList.setHasFixedSize(true)
        binding.vehicleList.layoutManager = LinearLayoutManager(viewVehicle.context)
        binding.vehicleList.adapter = adapter

        vehicleViewModel.state.observe(viewLifecycleOwner){ state ->
            when (state) {
                // just checking equality because Loading is a -singleton object instance-
                StateVehicle.Loading -> {
                    // TODO: If you need do something in loading
                }
                // Error and Success are both -classes- so we need to check their type with 'is'
                is StateVehicle.Error -> {
                    // TODO: If you need do something in error
                }
                is StateVehicle.SuccessList -> {
                    state.vehicleList?.let { adapter.setVehicleList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }
        }
        vehicleViewModel.findAllVehicles()
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.reparation_filter,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            vehicleFilter.adapter = adapter
        }
    }

    override fun onQueryTextSubmit(p0: String): Boolean {
        val filterSpinner: String = vehicleFilter.selectedItem.toString()

        if (p0 != "") {
            println(p0)
            adapter.filter(p0, filterSpinner)
        }
        return false;
    }

    override fun onQueryTextChange(p0: String): Boolean {
        if (p0 == "") {
            initRecycler()
        }
        return false;
    }


}