package cr.una.bravo.bravofrontend.view.reparation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.ReparationCardAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentSearchReparationBinding
import cr.una.bravo.bravofrontend.repository.ReportRepository
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import cr.una.bravo.bravofrontend.service.ReportService
import cr.una.bravo.bravofrontend.service.VehicleService
import cr.una.bravo.bravofrontend.viewmodel.*


class SearchReparationFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentSearchReparationBinding
    private val reparationViewModel: ReparationViewModel by activityViewModels{
        ReparationViewModelFactory()
    }

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

        //Retrofit Service
        val reportService = ReportService.getInstance()
        val reportRepository = ReportRepository(reportService)

        //ViewModelfactory
       /* reparationViewModel =
            ViewModelProvider(this, ReparationViewModelFactory())[ReparationViewModel::class.java]*/

        //RecyclerView
        recyclerView = binding.reparationList//viewReparation.findViewById(R.id.reparationList)
        initRecycler()

        //Spinner
        reparationFilter = binding.reparationFilter//viewReparation.findViewById(R.id.reparationFilter)
        initSpinner()

        //Search View
        searcher = binding.reparationSearch//viewReparation.findViewById(R.id.reparationSearch)
        searcher.setOnQueryTextListener(this)

        //Return Button
        binding.btnSearchRepReturn.setOnClickListener{
            Navigation.findNavController(viewReparation)
                .navigate(R.id.action_searchReparationFragment_to_mainFragment)
        }
        /*viewReparation.findViewById<ImageButton>(R.id.btn_SearchRep_Return).setOnClickListener {
            Navigation.findNavController(viewReparation)
                .navigate(R.id.action_searchReparationFragment_to_mainFragment)
        }*/

        return viewReparation
    }

    private fun initRecycler() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(viewReparation.context)
        recyclerView.adapter = adapter

        reparationViewModel.state.observe(viewLifecycleOwner){ state ->
            when (state) {
                // just checking equality because Loading is a -singleton object instance-
                StateReparation.Loading -> {
                    // TODO: If you need do something in loading
                }
                // Error and Success are both -classes- so we need to check their type with 'is'
                is StateReparation.Error -> {
                    // TODO: If you need do something in error
                }
                is StateReparation.SuccessList -> {
                    state.reportList?.let { adapter.setReparationsList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }
        }
        reparationViewModel.findAllReparations()
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