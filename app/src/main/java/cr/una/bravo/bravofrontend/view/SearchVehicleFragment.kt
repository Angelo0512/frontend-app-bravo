package cr.una.bravo.bravofrontend.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Spinner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.adapter.VehicleCardAdapter

import cr.una.bravo.bravofrontend.model.Vehicle

class SearchVehicleFragment : Fragment(), SearchView.OnQueryTextListener {
    lateinit var viewVehicle: View
    lateinit var recyclerView: RecyclerView
    lateinit var vehicleFilter: Spinner
    lateinit var searcher: SearchView
    lateinit var adapter: VehicleCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewVehicle = inflater.inflate(R.layout.fragment_search_vehicle, container, false)

        //RecyclerView
        recyclerView = viewVehicle.findViewById(R.id.vehicleList)
        initRecycler()

        //Spinner
        vehicleFilter = viewVehicle.findViewById(R.id.vehicleFilter)
        initSpinner()

        //Search View
        searcher = viewVehicle.findViewById(R.id.vehicleSearch)
        searcher.setOnQueryTextListener(this)

        //Return Button
        viewVehicle.findViewById<ImageButton>(R.id.btn_SearchVeh_Return).setOnClickListener {
            Navigation.findNavController(viewVehicle)
                .navigate(R.id.action_searchVehicleFragment2_to_mainFragment)
        }

        return viewVehicle
    }

    private fun initRecycler() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(viewVehicle.context)
        val cardList: MutableList<Vehicle> = mutableListOf(
            Vehicle(
                id = 1,
                plateNumber = "1234",
                vinNumber = "12456123",
                brand = "Motorola",
                motorSerial = "12345",
                vehicleClass = "Deportivo",
                motorType = "Electrico",
            ),
            Vehicle(
                id = 1,
                plateNumber = "1234",
                vinNumber = "12456123",
                brand = "Motorola",
                motorSerial = "12345",
                vehicleClass = "Deportivo",
                motorType = "Electrico",
            ),
            Vehicle(
                id = 1,
                plateNumber = "1234",
                vinNumber = "12456123",
                brand = "Motorola",
                motorSerial = "12345",
                vehicleClass = "Deportivo",
                motorType = "Electrico",
            ),
        )
        adapter = VehicleCardAdapter(cardList)
        recyclerView.adapter = this.adapter
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.reparation_filter,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            vehicleFilter.adapter = adapter
        }
    }

    override fun onQueryTextSubmit(p0: String): Boolean {
        val filterSpinner: String = vehicleFilter.selectedItem.toString()

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