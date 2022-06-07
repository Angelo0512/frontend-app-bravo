package cr.una.bravo.bravofrontend.view.reparation

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
import cr.una.bravo.bravofrontend.adapter.ClientAdapter

/**
 * A simple [Fragment] subclass.
 */
class InsertClientFragment : Fragment() {

    var tabTitle = arrayOf("Cliente Nuevo", "Cliente Existente")
    private lateinit var clientAdapter: ClientAdapter
    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clientAdapter = ClientAdapter(this)
        viewPager = view.findViewById(R.id.addClientPager)
        viewPager.adapter = clientAdapter
        val tabLayout = view.findViewById<TabLayout>(R.id.addClientTabs)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insert_client, container, false)

        view.findViewById<ImageButton>(R.id.btn_AddClient_Return).setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_insertClientFragment_to_insertVehicle
        ) }

        return view
    }

}