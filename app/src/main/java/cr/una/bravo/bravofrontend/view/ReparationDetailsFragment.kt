package cr.una.bravo.bravofrontend.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.ReparationDetailsAdapter
import cr.una.bravo.bravofrontend.model.Service


class ReparationDetailsFragment : Fragment() {
    lateinit var viewReparation : View
    lateinit var recyclerView: RecyclerView
    lateinit var adapter : ReparationDetailsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewReparation = inflater.inflate(R.layout.fragment_reparation_details, container, false)

        //RecyclerView
        recyclerView = viewReparation.findViewById(R.id.service_List)
        initRecycler()

        return viewReparation
    }
    private fun initRecycler(){
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(viewReparation.context)
        val cardList : MutableList<Service> = mutableListOf(
            Service(
                id = 1,
                name = "No hubo problema",
                observations = null,
                state = "Terminado",
            ),
            Service(
                id = 1,
                name = "No hubo problema",
                observations = null,
                state = "Terminado",
            ),
            Service(
                id = 1,
                name = "No hubo problema",
                observations = null,
                state = "Terminado",
            ),
            Service(
                id = 1,
                name = "No hubo problema",
                observations = null,
                state = "Terminado",
            ),
        )
        adapter = ReparationDetailsAdapter(cardList)
        recyclerView.adapter = this.adapter
    }

}