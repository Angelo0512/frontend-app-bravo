package cr.una.bravo.bravofrontend.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cr.una.bravo.bravofrontend.adapter.ClientReportAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentExistingClientBinding
import cr.una.bravo.bravofrontend.viewmodel.ClientViewModel
import cr.una.bravo.bravofrontend.viewmodel.ClientViewModelFactory
import cr.una.bravo.bravofrontend.viewmodel.StateClient

/**
 * A simple [Fragment] subclass.
 */
class ExistingClientFragment : Fragment() {
    // Definition of the binding variable
    private var _binding: FragmentExistingClientBinding? = null
    private val binding get() = _binding!!

    private lateinit var clientViewModel: ClientViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = ClientReportAdapter()
        _binding = FragmentExistingClientBinding.inflate(inflater, container, false)


        //ViewModelfactory
        clientViewModel =
            ViewModelProvider(this, ClientViewModelFactory())[ClientViewModel::class.java]

        binding.clientExistingList.setHasFixedSize(true)
        binding.clientExistingList.layoutManager = LinearLayoutManager(binding.root.context)
        binding.clientExistingList.adapter = adapter

        clientViewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                // just checking equality because Loading is a -singleton object instance-
                StateClient.Loading -> {
                    // TODO: If you need do something in loading
                }
                // Error and Success are both -classes- so we need to check their type with 'is'
                is StateClient.Error -> {
                    // TODO: If you need do something in error
                }
                is StateClient.SuccessList -> {
                    state.userList?.let { adapter.setUserList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }

        }
        clientViewModel.findAllUsers()
        return binding.root
    }
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_existing_client, container, false)
    }

