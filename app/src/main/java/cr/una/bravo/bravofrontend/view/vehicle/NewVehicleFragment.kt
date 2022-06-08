package cr.una.bravo.bravofrontend.view.vehicle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.navigation.Navigation
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.FragmentNewVehicleBinding
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModel

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
  

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=  FragmentNewVehicleBinding.inflate(inflater,container, false)

        val spinnerTipo: Spinner = view.findViewById(R.id.field_Vehicle_Tipo)
        val adapterTipo: ArrayAdapter<String> = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_spinner_dropdown_item,
            itemListTipo
        )
        spinnerTipo.adapter = adapterTipo

        val spinnerTipoMotor: Spinner = view.findViewById(R.id.field_Vehicle_TipoMotor)
        val adapterTipoMotor: ArrayAdapter<String> = ArrayAdapter<String>(
            view.context,
            android.R.layout.simple_spinner_dropdown_item,
            itemListTipoMotor
        )
        spinnerTipoMotor.adapter = adapterTipoMotor

        view.findViewById<Button>(R.id.btn_Vehicle_SubmitNew).setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_insertVehicle_to_insertClientFragment
        ) }

        return view
    }

}