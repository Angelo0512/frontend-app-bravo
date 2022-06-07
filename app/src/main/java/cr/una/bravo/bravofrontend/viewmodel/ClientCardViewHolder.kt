package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ClientListItemBinding
import cr.una.bravo.bravofrontend.model.UserBasic

class ClientCardViewHolder(
    private val clientListItemBinding: ClientListItemBinding
) : RecyclerView.ViewHolder(clientListItemBinding.root)
{
    fun bindClientCard(clientCard : UserBasic){
        clientListItemBinding.clientId.text = clientCard.id.toString()
        clientListItemBinding.clientName.text = clientCard.firstName + " " + clientCard.lastName
        clientListItemBinding.clientEmail.text = clientCard.email
    }
}