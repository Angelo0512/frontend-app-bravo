package cr.una.bravo.bravofrontend.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.FragmentNewClientBinding
import cr.una.bravo.bravofrontend.model.User
import cr.una.bravo.bravofrontend.viewmodel.ClientViewModel
import cr.una.bravo.bravofrontend.viewmodel.ClientViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class NewClientFragment : Fragment() {
    private var _binding: FragmentNewClientBinding? = null
    private val binding get() = _binding!!
    private lateinit var clientViewModel: ClientViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_new_client, container, false)

        //view.findViewById<Button>(R.id.btn_Client_SubmitNew).setOnClickListener { Navigation.findNavController(view).navigate(
        //    R.id.action_insertClientFragment_to_insertServiceFragment
        //) }

        //return view
        _binding=  FragmentNewClientBinding.inflate(inflater,container, false)
        clientViewModel = ViewModelProvider(this, ClientViewModelFactory())[ClientViewModel::class.java]

        binding.btnClientSubmitNew.setOnClickListener {
            clientViewModel.createUser(
                User(
                    id = binding.fieldClientID.text.toString().toLong() ,
                    firstName = binding.fieldClientName.text.toString(),
                    lastName = binding.fieldClientLastName.text.toString(),
                    email = binding.fieldClientEmail.text.toString(),
                    password = "some_random_wea",
                    enabled= true,
                    roles = null,
                    tokenExpired = true,
                    createDate = null
                )
            )
            val bundle = bundleOf("user_id" to binding.fieldClientID.text.toString())
            findNavController().navigate(R.id.action_insertClientFragment_to_insertServiceFragment, bundle)

        }

        return binding.root

    }
}