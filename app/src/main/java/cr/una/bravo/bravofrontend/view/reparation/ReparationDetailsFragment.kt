package cr.una.bravo.bravofrontend.view.reparation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.ReparationCardAdapter.Companion.REPORT_ID
import cr.una.bravo.bravofrontend.adapter.ReparationDetailsAdapter
import cr.una.bravo.bravofrontend.databinding.FragmentReparationDetailsBinding
import cr.una.bravo.bravofrontend.databinding.FragmentSearchReparationBinding
import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.viewmodel.ReparationViewModel
import cr.una.bravo.bravofrontend.viewmodel.StateReparation


class ReparationDetailsFragment : Fragment() {
    private lateinit var binding: FragmentReparationDetailsBinding
    private val reparationViewModel: ReparationViewModel by activityViewModels()

    lateinit var viewReparation: View
    lateinit var recyclerView: RecyclerView
    private val adapter = ReparationDetailsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReparationDetailsBinding.inflate(inflater, container, false)
        viewReparation = binding.root


        val reportId: String = arguments?.getString(REPORT_ID) ?: "0"
        initRecycler()
        reparationViewModel.state.observe(viewLifecycleOwner){ state ->
            with(binding.root){
                when(state){
                    StateReparation.Loading -> {
                        // TODO: If you need do something in loading
                    }
                    // Error and Success are both -classes- so we need to check their type with 'is'
                    is StateReparation.Error -> {
                        // TODO: If you need do something in error
                    }
                    is StateReparation.Success -> {
                        state.Report?.let {
                            binding.idText.text= it.id.toString()
                            binding.clientText.text = it.client.firstName + " " + it.client.lastName
                            binding.plateText.text = it.vehicle.plateNumber


                            it.services?.let { it1 -> adapter.setServiceList(it1) }
                        }
                    }
                    else -> {
                        // TODO: Not state loaded
                    }

                }
            }

        }

        reparationViewModel.getReparation(reportId.toLong())

        //RecyclerView
        recyclerView = viewReparation.findViewById(R.id.service_List)




        return viewReparation
    }
    private fun initRecycler(){
        binding.serviceList.setHasFixedSize(true)
        binding.serviceList.layoutManager = LinearLayoutManager(viewReparation.context)
        binding.serviceList.adapter = adapter
    }

}