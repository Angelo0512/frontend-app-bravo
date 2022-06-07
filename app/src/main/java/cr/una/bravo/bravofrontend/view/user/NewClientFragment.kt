package cr.una.bravo.bravofrontend.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import cr.una.bravo.bravofrontend.R

/**
 * A simple [Fragment] subclass.
 */
class NewClientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_client, container, false)

        view.findViewById<Button>(R.id.btn_Client_SubmitNew).setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_insertClientFragment_to_insertServiceFragment
        ) }

        return view
    }
}