package cr.una.bravo.bravofrontend.view.reparation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.FragmentInsertServiceBinding
import cr.una.bravo.bravofrontend.databinding.FragmentNewVehicleBinding
import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.utils.ServiceDialog

/**
 * A simple [Fragment] subclass.
 */
class InsertServiceFragment : Fragment() {
    private var _binding: FragmentInsertServiceBinding? = null
    private val binding get() = _binding!!
    private var services = mutableListOf<Service>()
    private lateinit var chipGroup: ChipGroup

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

        binding.btnServiceReturn.setOnClickListener { Navigation.findNavController(binding.root).navigate(
            R.id.action_insertServiceFragment_to_insertClientFragment
        ) }

        binding.btnServiceEnd.setOnClickListener { Navigation.findNavController(binding.root).navigate(
            R.id.action_insertServiceFragment_to_mainFragment
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
        return binding.root
    }

}