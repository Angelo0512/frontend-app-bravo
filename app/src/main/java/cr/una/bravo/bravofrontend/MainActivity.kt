package cr.una.bravo.bravofrontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val profile= SearchReparationFragment()
        supportFragmentManager.beginTransaction().add(R.id.container, profile).commit()
    }
}