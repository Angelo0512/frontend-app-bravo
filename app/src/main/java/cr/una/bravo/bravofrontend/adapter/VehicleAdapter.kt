package cr.una.bravo.bravofrontend.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cr.una.bravo.bravofrontend.view.vehicle.ExistingVehicleFragment
import cr.una.bravo.bravofrontend.view.vehicle.NewVehicleFragment

class VehicleAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return NewVehicleFragment()
            1 -> return ExistingVehicleFragment()
            else -> return NewVehicleFragment()
        }
    }
}