package cr.una.bravo.bravofrontend.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.ReparationCardAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentSearchReparationBinding
import cr.una.bravo.bravofrontend.model.Report
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.viewmodel.ReparationViewModel
import java.util.*


class SearchReparationFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentSearchReparationBinding
    private val reparationViewModel: ReparationViewModel by viewModels()
    private val adapter = ReparationCardAdapter()

    lateinit var viewReparation: View
    lateinit var recyclerView: RecyclerView
    lateinit var reparationFilter: Spinner
    lateinit var searcher: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchReparationBinding.inflate(inflater, container, false)
        viewReparation = binding.root

        //RecyclerView
        recyclerView = viewReparation.findViewById(R.id.reparationList)
        initRecycler()

        //Spinner
        reparationFilter = viewReparation.findViewById(R.id.reparationFilter)
        initSpinner()

        //Search View
        searcher = viewReparation.findViewById(R.id.reparationSearch)
        searcher.setOnQueryTextListener(this)

        //Return Button
        viewReparation.findViewById<ImageButton>(R.id.btn_SearchRep_Return).setOnClickListener {
            Navigation.findNavController(viewReparation)
                .navigate(R.id.action_searchReparationFragment_to_mainFragment)
        }

        return viewReparation
    }

    private fun initRecycler() {
        binding.reparationList.setHasFixedSize(true)
        binding.reparationList.layoutManager = LinearLayoutManager(viewReparation.context)
        binding.reparationList.adapter = adapter

        reparationViewModel.reportList.observe(viewLifecycleOwner){
            adapter.setReparationsList(it)
        }
        reparationViewModel.findAllTask()
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.reparation_filter,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            reparationFilter.adapter = adapter
        }
    }

    override fun onQueryTextSubmit(p0: String): Boolean {
        val filterSpinner: String = reparationFilter.selectedItem.toString()

        if (p0 != "") {
            println(p0)
            adapter.filter(p0, filterSpinner)
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