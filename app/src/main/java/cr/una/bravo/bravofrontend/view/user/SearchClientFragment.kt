package cr.una.bravo.bravofrontend.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.adapter.ClientCardAdapter
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.FragmentSearchClientBinding
import cr.una.bravo.bravofrontend.repository.UserRepository
import cr.una.bravo.bravofrontend.service.UserService
import cr.una.bravo.bravofrontend.viewmodel.ClientViewModel
import cr.una.bravo.bravofrontend.viewmodel.ClientViewModelFactory
import cr.una.bravo.bravofrontend.viewmodel.StateClient
import cr.una.bravo.bravofrontend.viewmodel.StateVehicle

class SearchClientFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentSearchClientBinding
    private lateinit var clientViewModel: ClientViewModel
    private val adapter = ClientCardAdapter()

    lateinit var viewClient: View
    lateinit var recyclerView: RecyclerView
    lateinit var searcher: SearchView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //View
        binding = FragmentSearchClientBinding.inflate(inflater, container, false)
        viewClient = binding.root

        //ViewModelFactory
        clientViewModel =
                ViewModelProvider(this,ClientViewModelFactory())[ClientViewModel::class.java]
        //Recycler
        recyclerView = binding.clientList
        initRecycler()

        //Searcher
        searcher = binding.clientSearch
        searcher.setOnQueryTextListener(this)

        //Return Button
        binding.btnSearchClientReturn.setOnClickListener {
            Navigation.findNavController(viewClient)
                .navigate(R.id.action_searchClientFragment2_to_mainFragment)
        }
        
        return viewClient
    }

    private fun initRecycler() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(viewClient.context)
        recyclerView.adapter = adapter

        clientViewModel.state.observe(viewLifecycleOwner){ state ->
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
                    state.userList?.let { adapter.setClientList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }
        }
        clientViewModel.findAllUsers()
    }

    override fun onQueryTextSubmit(p0: String): Boolean {

        if (p0 != "") {
            println(p0)
            adapter.filter(p0)
        }
        return false;
    }

    override fun onQueryTextChange(p0: String): Boolean {
        if (p0 == "") {
            initRecycler()
        }
        return false;
    }

}