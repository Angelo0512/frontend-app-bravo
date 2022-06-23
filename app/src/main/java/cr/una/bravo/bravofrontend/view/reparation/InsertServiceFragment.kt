package cr.una.bravo.bravofrontend.view.reparation

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.FragmentInsertServiceBinding
import cr.una.bravo.bravofrontend.databinding.FragmentNewVehicleBinding
import cr.una.bravo.bravofrontend.model.Report
import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.service.ReportService
import cr.una.bravo.bravofrontend.utils.ServiceDialog
import cr.una.bravo.bravofrontend.viewmodel.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class InsertServiceFragment : Fragment() {
    private var _binding: FragmentInsertServiceBinding? = null
    private val binding get() = _binding!!
    private var services = mutableListOf<Service>()
    private var serviceResponse = mutableListOf<Service>()
    private lateinit var chipGroup: ChipGroup
    private lateinit var serviceViewModel: ServiceViewModel
    private lateinit var reportViewModel: ReparationViewModel
    private fun createServices(){
        for(service in services){
            serviceViewModel.createService(
                service
            )
        }
    }
    private fun deleteService(text:String){
        services = services.filter { !it.name.equals(text) }.toMutableList()
        binding.prueba.text= services.toString()

    }
    private fun addChip(text: String){
        val chip = Chip(this.context)
        chip.text = text
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener{
            deleteService(chip.text.toString())
            chipGroup.removeView(chip)
        }
        chipGroup.addView(chip)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=  FragmentInsertServiceBinding.inflate(inflater,container, false)

        // ServiceViewModelFactory
        serviceViewModel =
            ViewModelProvider(this, ServiceViewModelFactory())[ServiceViewModel::class.java]

        // ReparationViewModelFactory
        reportViewModel =
            ViewModelProvider(this, ReparationViewModelFactory())[ReparationViewModel::class.java]

        binding.btnServiceReturn.setOnClickListener { Navigation.findNavController(binding.root).navigate(
            R.id.action_insertServiceFragment_to_insertClientFragment
        ) }

        serviceViewModel.state.observe(viewLifecycleOwner){ state ->
            with(binding.root){
                when(state){
                    StateService.Loading -> {
                        // TODO: If you need do something in loading
                    }
                    // Error and Success are both -classes- so we need to check their type with 'is'
                    is StateService.Error -> {
                        // TODO: If you need do something in error
                    }
                    is StateService.Success -> {
                        state.service?.let {
                            serviceResponse.add(it)
                        }
                    }
                    else -> {
                        // TODO: Not state loaded
                    }

                }
            }

        }

        binding.btnServiceEnd.setOnClickListener {
            // TODO: Crear la reparacion
           /*
           createServices()
           reportViewModel.createReparation(
                Report(
                    creationDate = Date(),
                    description = binding.descriptionReport.text.toString(),
                    services = serviceResponse,
                    client= cliente,
                    vehicle = vehicle,
                    technician = technician,
                )
            )*/
            Navigation.findNavController(binding.root).navigate(R.id.action_insertServiceFragment_to_mainFragment
        ) }

        chipGroup= binding.chipGroup
        binding.btnServiceAddMore.setOnClickListener{
            ServiceDialog(
                onSubmitClickListener = { service ->
                    Toast.makeText(requireContext(), "Servicio ingresado correctamente", Toast.LENGTH_SHORT).show()
                    services.add(service)
                    binding.prueba.text= services.toString()
                    addChip(service.name)

                }

            ).show(parentFragmentManager, "dialog")

        }

        //val inputText = arguments?.getString("user_id")
        //val alertDialog = AlertDialog.Builder(context).setTitle("user_id").setMessage(inputText).show();
        return binding.root
    }

}