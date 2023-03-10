package cr.una.bravo.bravofrontend.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding
import cr.una.bravo.bravofrontend.model.Report
import cr.una.bravo.bravofrontend.viewmodel.ReparationCardViewHolder

class ReparationCardAdapter :
    RecyclerView.Adapter<ReparationCardViewHolder>() {
    private var reparationCards = mutableListOf<Report>()
    private lateinit var reparationCardsOriginal: MutableList<Report>

    @SuppressLint("NotifyDataSetChanged")
    fun setReparationsList(reports : List<Report>){
        this.reparationCards = reports.toMutableList()
        this.reparationCardsOriginal = reparationCards
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReparationCardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ReparationsListItemBinding.inflate(from, parent, false)
        return ReparationCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReparationCardViewHolder, position: Int) {
        holder.bindReparationCard(reparationCards[position])

        holder.reparationsListItemBinding.root.setOnClickListener{
            val bundle = bundleOf(REPORT_ID to reparationCards[position].id.toString())
            holder.reparationsListItemBinding.root.findNavController().navigate(
                R.id.action_searchReparationFragment_to_reparationDetailsFragment, bundle
            )
        }
    }

    override fun getItemCount(): Int = reparationCards.size

    companion object {
        const val REPORT_ID = "report_id"
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filter(SearchText: String, spinnerFilter: String) {
        lateinit var newList: MutableList<Report>
        if (spinnerFilter == "Cliente")
            newList = reparationCardsOriginal.filter {
                it.client.id.toString() == SearchText
            } as MutableList<Report>
        if (spinnerFilter == "Placa")
            newList = reparationCardsOriginal.filter {
                it.vehicle.plateNumber == SearchText
            } as MutableList<Report>
        reparationCards.clear()
        reparationCards.addAll(newList)
        for (r in reparationCards)
            notifyDataSetChanged()
    }

}

