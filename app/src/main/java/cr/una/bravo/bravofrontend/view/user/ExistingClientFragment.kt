package cr.una.bravo.bravofrontend.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.FragmentExistingClientBinding
import cr.una.bravo.bravofrontend.viewmodel.ClientViewModel

/**
 * A simple [Fragment] subclass.
 */
class ExistingClientFragment : Fragment() {
    // Definition of the binding variable
    private var _binding: FragmentExistingClientBinding? = null
    private val binding get() = _binding!!

    private lateinit var clientViewModel: ClientViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_existing_client, container, false)
    }

}