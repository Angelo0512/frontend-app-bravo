package cr.una.bravo.bravofrontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ReparationCard
import cr.una.bravo.bravofrontend.data.model.ServiceCard


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
        val cardList : MutableList<ServiceCard> = mutableListOf(
            ServiceCard(
                serviceId = "Cambio de llantas",
                observation = "No hubo problema",
                state = "Terminado",

            ),
            ServiceCard(
                serviceId = "Cambio aceite",
                observation = "No hubo problema",
                state = "Pendiente",
            ),
            ServiceCard(
                serviceId = "Reparacion motor",
                observation = "No hubo problema",
                state = "Pendiente",
            ),
            ServiceCard(
                serviceId = "Reparacion pintura",
                observation = "No hubo problema",
                state = "En proceso",
            )
        )
        adapter = ReparationDetailsAdapter(cardList)
        recyclerView.adapter = this.adapter
    }

}