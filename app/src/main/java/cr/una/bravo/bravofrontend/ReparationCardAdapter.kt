package cr.una.bravo.bravofrontend

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ReparationCard
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding

class ReparationCardAdapter(
    private val reparationCards: MutableList<ReparationCard>,
) :
    RecyclerView.Adapter<ReparationCardViewHolder>() {


    private var reparationCardsOriginal: MutableList<ReparationCard> = reparationCards

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReparationCardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ReparationsListItemBinding.inflate(from, parent, false)
        return ReparationCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReparationCardViewHolder, position: Int) {
        holder.bindReparationCard(reparationCards[position])
    }

    override fun getItemCount(): Int = reparationCards.size

    @SuppressLint("NotifyDataSetChanged")
    fun filter(SearchText: String, spinnerFilter: String) {
        lateinit var newList: MutableList<ReparationCard>
        if (spinnerFilter == "Cliente")
            newList = reparationCardsOriginal.filter {
                it.clientId.replace(
                    "Cedula: ",
                    ""
                ) == SearchText
            } as MutableList<ReparationCard>
        if (spinnerFilter == "Placa")
            newList = reparationCardsOriginal.filter {
                it.vehiclePlate.replace(
                    "Placa Vehiculo: ",
                    ""
                ) == SearchText
            } as MutableList<ReparationCard>
        reparationCards.clear()
        reparationCards.addAll(newList)
        for (r in reparationCards)
            notifyDataSetChanged()
    }

}

