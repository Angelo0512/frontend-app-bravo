package cr.una.bravo.bravofrontend.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.viewmodel.VehicleAdapter

/**
 * A simple [Fragment] subclass.
 */
class InsertVehicle : Fragment() {

    var tabTitle = arrayOf("Vehículo Nuevo", "Vehículo Existente")
    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vehicleAdapter = VehicleAdapter(this)
        viewPager = view.findViewById(R.id.addVehiclePager)
        viewPager.adapter = vehicleAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.addVehicleTabs)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insert_vehicle, container, false)

        view.findViewById<ImageButton>(R.id.btn_AddVehicle_Return).setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_insertVehicle_to_mainFragment
        ) }

        return view
    }
}