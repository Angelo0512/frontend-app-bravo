package cr.una.bravo.bravofrontend.view.vehicle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.Navigation
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.VehicleReportAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentNewVehicleBinding
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModel
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class NewVehicleFragment : Fragment() {

    private var itemListTipo: Array<String> = arrayOf("Van", "Pickup", "Deportivo", "Convertible")
    private var itemListTipoMotor: Array<String> = arrayOf("Electrico", "Diesel", "Gasolina")

    private var _binding: FragmentNewVehicleBinding? = null
    private val binding get() = _binding!!

    // View model
    private lateinit var vehicleViewModel: VehicleViewModel
    //Select item of spinner
    private lateinit var typeSelected: String
    private lateinit var motorSelected: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=  FragmentNewVehicleBinding.inflate(inflater,container, false)

        // VehicleViewModelFactory
        vehicleViewModel =
            ViewModelProvider(this, VehicleViewModelFactory())[VehicleViewModel::class.java]

        // Init spinners
        val spinnerTipo: Spinner = binding.fieldVehicleTipo
        val adapterTipo: ArrayAdapter<String> = ArrayAdapter<String>(
            activity?.applicationContext!!,
            android.R.layout.simple_spinner_dropdown_item,
            itemListTipo
        )
        spinnerTipo.adapter = adapterTipo

        val spinnerTipoMotor: Spinner = binding.fieldVehicleTipoMotor
        val adapterTipoMotor: ArrayAdapter<String> = ArrayAdapter<String>(
            activity?.applicationContext!!,
            android.R.layout.simple_spinner_dropdown_item,
            itemListTipoMotor
        )
        spinnerTipoMotor.adapter = adapterTipoMotor


        // Spinner select event
        typeSelected = spinnerTipo.selectedItem.toString()
        motorSelected = spinnerTipoMotor.selectedItem.toString()


    binding.btnVehicleSubmitNew.setOnClickListener {
        val plate = binding.fieldVehiclePlaca.text.toString()
        vehicleViewModel.createVehicle(
            Vehicle(
                plateNumber = plate ,
                vinNumber = binding.fieldVehicleVIN.text.toString(),
                brand = binding.fieldVehicleMarca.text.toString(),
                motorSerial = binding.fieldVehicleSerie.text.toString(),
                vehicleClass = spinnerTipo.selectedItem.toString(),
                motorType= spinnerTipoMotor.selectedItem.toString(),
            )
        )
        val bundle = Bundle()
        bundle.putString("vehicle_id", plate)
        findNavController().navigate(R.id.action_insertVehicle_to_insertClientFragment, bundle) }

        return binding.root
    }

}