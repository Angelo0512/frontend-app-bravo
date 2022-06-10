package cr.una.bravo.bravofrontend.view.vehicle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.VehicleReportAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentExistingVehicleBinding
import cr.una.bravo.bravofrontend.viewmodel.StateVehicle
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModel
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class ExistingVehicleFragment : Fragment() {

    // Definition of the binding variable
    private var _binding: FragmentExistingVehicleBinding? = null
    private val binding get() = _binding!!

    private lateinit var vehicleViewModel: VehicleViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = VehicleReportAdapter()
        _binding = FragmentExistingVehicleBinding.inflate(inflater, container, false)


        //ViewModelfactory
        vehicleViewModel =
            ViewModelProvider(this, VehicleViewModelFactory())[VehicleViewModel::class.java]

        binding.vehicleExistingList.setHasFixedSize(true)
        binding.vehicleExistingList.layoutManager = LinearLayoutManager(binding.root.context)
        binding.vehicleExistingList.adapter = adapter

        vehicleViewModel.state.observe(viewLifecycleOwner) { state ->

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
        return binding.root
    }

}