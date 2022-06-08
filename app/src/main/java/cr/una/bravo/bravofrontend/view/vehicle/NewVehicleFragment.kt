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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.Navigation
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.FragmentNewVehicleBinding
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModel
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class NewVehicleFragment : Fragment() {

    private var itemListTipo: Array<String> = arrayOf("1", "2", "3")
    private var itemListTipoMotor: Array<String> = arrayOf("4", "5", "6")

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
        vehicleViewModel.createVehicle(
            Vehicle(
                plateNumber = binding.fieldVehiclePlaca.text.toString() ,
                vinNumber = binding.fieldVehicleVIN.text.toString(),
                brand = binding.fieldVehicleMarca.text.toString(),
                motorSerial = binding.fieldVehicleSerie.text.toString(),
                vehicleClass = typeSelected,
                motorType= motorSelected,
            )
        )
        findNavController().navigate(R.id.action_insertVehicle_to_insertClientFragment) }

        return binding.root
    }

}