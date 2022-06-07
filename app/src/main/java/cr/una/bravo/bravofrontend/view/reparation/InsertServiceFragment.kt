package cr.una.bravo.bravofrontend.view.reparation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.Navigation
import cr.una.bravo.bravofrontend.R

/**
 * A simple [Fragment] subclass.
 */
class InsertServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insert_service, container, false)

        view.findViewById<ImageButton>(R.id.btn_Service_Return).setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_insertServiceFragment_to_insertClientFragment
        ) }
        view.findViewById<Button>(R.id.btn_Service_End).setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_insertServiceFragment_to_mainFragment
        ) }

        return view
    }

}