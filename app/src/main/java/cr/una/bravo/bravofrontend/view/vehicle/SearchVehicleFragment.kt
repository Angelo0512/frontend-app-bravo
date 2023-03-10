package cr.una.bravo.bravofrontend.view.vehicle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.VehicleCardAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentSearchVehicleBinding
import cr.una.bravo.bravofrontend.viewmodel.StateVehicle
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModel
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModelFactory

class SearchVehicleFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentSearchVehicleBinding
    private lateinit var vehicleViewModel: VehicleViewModel
    private val adapter = VehicleCardAdapter()

    private lateinit var viewVehicle: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var searcher: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchVehicleBinding.inflate(inflater, container, false)
        viewVehicle = binding.root

        //ViewModelfactory
        vehicleViewModel =
                ViewModelProvider(this, VehicleViewModelFactory())[VehicleViewModel::class.java]

        //RecyclerView
        recyclerView = binding.vehicleList
        initRecycler()

        //Search View
        searcher = binding.vehicleSearch
        searcher.setOnQueryTextListener(this)

        //Return Button
        binding.btnSearchVehReturn.setOnClickListener{
            Navigation.findNavController(viewVehicle)
                .navigate(R.id.action_searchVehicleFragment2_to_mainFragment)
        }

        return viewVehicle
    }

    private fun initRecycler() {

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(viewVehicle.context)
        recyclerView.adapter = adapter

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

    override fun onQueryTextSubmit(p0: String): Boolean {
        if (p0 != "") {
            println(p0)
            adapter.filter(p0)
        }
        return false
    }

    override fun onQueryTextChange(p0: String): Boolean {
        if (p0 == "") {
            initRecycler()
        }
        return false
    }
}