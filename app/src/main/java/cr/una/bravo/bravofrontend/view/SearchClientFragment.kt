package cr.una.bravo.bravofrontend.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.adapter.ClientCardAdapter
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.FragmentSearchClientBinding
import cr.una.bravo.bravofrontend.databinding.FragmentSearchReparationBinding
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.viewmodel.ClientViewModel
import cr.una.bravo.bravofrontend.viewmodel.VehicleViewModel
import java.util.*

class SearchClientFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentSearchClientBinding
    private val clientViewModel: ClientViewModel by viewModels()
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

        //Recycler
        recyclerView = viewClient.findViewById(R.id.clientList)
        initRecycler()

        //Searcher
        searcher = viewClient.findViewById(R.id.clientSearch)
        searcher.setOnQueryTextListener(this)

        //Return Button
        viewClient.findViewById<ImageButton>(R.id.btn_SearchClient_Return).setOnClickListener {
            Navigation.findNavController(viewClient)
                .navigate(R.id.action_searchClientFragment2_to_mainFragment)
        }

        return viewClient
    }

    private fun initRecycler() {
        binding.clientList.setHasFixedSize(true)
        binding.clientList.layoutManager = LinearLayoutManager(viewClient.context)
        binding.clientList.adapter = adapter

        clientViewModel.clientList.observe(viewLifecycleOwner){
            adapter.setClientList(it)
        }
        clientViewModel.findAllTask()
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