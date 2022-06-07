package cr.una.bravo.bravofrontend.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cr.una.bravo.bravofrontend.view.user.ExistingClientFragment
import cr.una.bravo.bravofrontend.view.user.NewClientFragment

class ClientAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return NewClientFragment()
            1 -> return ExistingClientFragment()
            else -> return NewClientFragment()
        }
    }

}