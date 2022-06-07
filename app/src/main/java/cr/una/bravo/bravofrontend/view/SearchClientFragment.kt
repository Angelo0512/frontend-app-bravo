package cr.una.bravo.bravofrontend.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.adapter.ClientCardAdapter
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.model.UserBasic
import java.util.*

class SearchClientFragment : Fragment(), SearchView.OnQueryTextListener {
    lateinit var viewClient: View
    lateinit var recyclerView: RecyclerView
    lateinit var searcher: SearchView
    lateinit var adapter: ClientCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //View
        viewClient = inflater.inflate(R.layout.fragment_search_client, container, false)

        //Recycler
        recyclerView = viewClient.findViewById(R.id.clientList)
        initRecycler()

        //Searcher
        searcher = viewClient.findViewById(R.id.clientSearch)
        searcher.setOnQueryTextListener(this)

        //Return Button
        viewClient.findViewById<ImageButton>(R.id.btn_SearchClient_Return).setOnClickListener {
            Navigation.findNavController(viewClient)
                .navigate(R.id.action_searchClientFragment2_to_mainFragment)
        }

        return viewClient
    }

    private fun initRecycler() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(viewClient.context)
        val cardList: MutableList<UserBasic> = mutableListOf(
            UserBasic(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                email = "myemail@test.com",
                createDate = Date()
            ),
            UserBasic(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                email = "myemail@test.com",
                createDate = Date()
            ),
            UserBasic(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                email = "myemail@test.com",
                createDate = Date()
            ),
            UserBasic(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                email = "myemail@test.com",
                createDate = Date()
            ),
        )
        adapter = ClientCardAdapter(cardList)
        recyclerView.adapter = this.adapter
    }

    override fun onQueryTextSubmit(p0: String): Boolean {

        if (p0 != "") {
            println(p0)
            adapter.filter(p0)
        }
        return false;
    }

    override fun onQueryTextChange(p0: String): Boolean {
        if (p0 == "") {
            initRecycler()
        }
        return false;
    }

}