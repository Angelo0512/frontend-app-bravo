package cr.una.bravo.bravofrontend.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ClientListItemBinding
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.viewmodel.ClientCardViewHolder

class ClientCardAdapter(
    private val clientCards: MutableList<UserBasic>
) : RecyclerView.Adapter<ClientCardViewHolder>() {

    private var clientCardsOriginal: MutableList<UserBasic> = clientCards

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
        lateinit var newList: MutableList<UserBasic>
        newList = clientCardsOriginal.filter {
            it.id == SearchText.toLong()
        } as MutableList<UserBasic>

        clientCards.clear()
        clientCards.addAll(newList)
        for (r in clientCards)
            notifyDataSetChanged()
    }
}