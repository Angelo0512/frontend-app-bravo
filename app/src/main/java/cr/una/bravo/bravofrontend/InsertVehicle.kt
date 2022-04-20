package cr.una.bravo.bravofrontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class InsertVehicle : AppCompatActivity() {

    var tabTitle = arrayOf("Vehículo Existente", "Vehículo Nuevo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_vehicle)

        val pager = findViewById<ViewPager2>(R.id.addVehiclePager)
        val tl = findViewById<TabLayout>(R.id.addVehicleTabs)
        pager.adapter = VehicleAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tl, pager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}