package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ClientCard
import cr.una.bravo.bravofrontend.databinding.ClientListItemBinding

class ClientCardViewHolder(
    private val clientListItemBinding: ClientListItemBinding
) : RecyclerView.ViewHolder(clientListItemBinding.root)
{
    fun bindClientCard(clientCard : ClientCard){
        clientListItemBinding.clientId.text = clientCard.clientId
        clientListItemBinding.clientName.text = clientCard.clientName
        clientListItemBinding.clientEmail.text = clientCard.clientEmail
        clientListItemBinding.clientTel.text = clientCard.clientTel
    }
}