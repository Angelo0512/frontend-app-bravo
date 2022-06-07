package cr.una.bravo.bravofrontend.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cr.una.bravo.bravofrontend.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [userProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class userProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        val name = arguments?.getString("userName") ?: "Ali Connors"
        val id = arguments?.getString("identificacion") ?: "12345678"
        val email = arguments?.getString("email") ?: "Ali@email.com"
        val telefono = arguments?.getString("telefono") ?: "88882222"
        view.findViewById<TextView>(R.id.identificacion_profile).text = id
        view.findViewById<TextView>(R.id.PersonName_profile).text = name
        view.findViewById<TextView>(R.id.email_profile).text = email
        view.findViewById<TextView>(R.id.telephone_profile).text = telefono
        return view
    }


}