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

        binding.btnServiceAddMore.setOnClickListener{
            ServiceDialog(
                onSubmitClickListener = { service ->
                    Toast.makeText(requireContext(), "Usted ingreso: ${service}", Toast.LENGTH_SHORT).show()
                    services.add(service)

                }

            ).show(parentFragmentManager, "dialog")

        }
        return binding.root
    }

}