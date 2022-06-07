package cr.una.bravo.bravofrontend.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.VehicleCardAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentSearchVehicleBinding
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import cr.una.bravo.bravofrontend.service.VehicleService
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

        vehicleViewModel.vehicleList.observe(viewLifecycleOwner){
            adapter.setVehicleList(it)
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