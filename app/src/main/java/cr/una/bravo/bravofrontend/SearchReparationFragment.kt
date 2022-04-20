package cr.una.bravo.bravofrontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ReparationCard
import cr.una.bravo.bravofrontend.data.model.reparationCardList
import cr.una.bravo.bravofrontend.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SearchReparationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchReparationFragment : Fragment() {
    private lateinit var  binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_search_reparation, container, false)

        //RecyclerView

        val recyclerView : RecyclerView = view.findViewById(R.id.reparationList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val cardList : List<ReparationCard> = mutableListOf(
            ReparationCard(
                reparationId = "1",
                clientId = "1",
                clientName = "John Doe",
                vehiclePlate = "1"
            ),
            ReparationCard(
                reparationId = "1",
                clientId = "1",
                clientName = "John Doe",
                vehiclePlate = "1"
            ),
            ReparationCard(
                reparationId = "1",
                clientId = "1",
                clientName = "John Doe",
                vehiclePlate = "1"
            ),
            ReparationCard(
                reparationId = "1",
                clientId = "1",
                clientName = "John Doe",
                vehiclePlate = "1"
            ),
            ReparationCard(
                reparationId = "1",
                clientId = "1",
                clientName = "John Doe",
                vehiclePlate = "1"
            )
        )
        recyclerView.adapter = ReparationCardAdapter(cardList)

        //Spinner
        val reparationFilter : Spinner = view.findViewById(R.id.reparationFilter)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.reparation_filter,
            android.R.layout.simple_spinner_item
        ).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            reparationFilter.adapter = adapter
        }
        return view
    }

    // For the mean while
    private fun populateReparations(){
        val rep1 = ReparationCard(
            reparationId = "1",
            clientId = "1",
            clientName = "John Doe",
            vehiclePlate = "1"
        )
        reparationCardList.add(rep1)
    }
}