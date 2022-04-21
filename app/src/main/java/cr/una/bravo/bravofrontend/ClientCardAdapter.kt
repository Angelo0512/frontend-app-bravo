package cr.una.bravo.bravofrontend

import android.annotation.SuppressLint
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ClientCard
import cr.una.bravo.bravofrontend.data.model.ReparationCard
import cr.una.bravo.bravofrontend.databinding.ClientListItemBinding

class ClientCardAdapter(
    private val clientCards: MutableList<ClientCard>
) : RecyclerView.Adapter<ClientCardViewHolder>() {

    private var clientCardsOriginal: MutableList<ClientCard> = clientCards

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientCardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ClientListItemBinding.inflate(from, parent, false)
        return ClientCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientCardViewHolder, position: Int) {
        holder.bindClientCard(clientCards[position])
    }

    override fun getItemCount(): Int = clientCards.size

    @SuppressLint("NotifyDataSetChanged")
    fun filter(SearchText: String) {
        lateinit var newList: MutableList<ClientCard>
        newList = clientCardsOriginal.filter {
            it.clientId.replace(
                "Identificación: ",
                ""
            ) == SearchText
        } as MutableList<ClientCard>

        clientCards.clear()
        clientCards.addAll(newList)
        for (r in clientCards)
            notifyDataSetChanged()
    }
}