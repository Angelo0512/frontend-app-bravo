package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ReparationCard
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding


class ReparationCardViewHolder(
     val reparationsListItemBinding: ReparationsListItemBinding
) : RecyclerView.ViewHolder(reparationsListItemBinding.root) {
    fun bindReparationCard(repCard: ReparationCard) {
        reparationsListItemBinding.reparationId.text = repCard.reparationId
        reparationsListItemBinding.clientId.text = repCard.clientId
        reparationsListItemBinding.clientName.text = repCard.clientName
        reparationsListItemBinding.vehiclePlate.text = repCard.vehiclePlate
    }
}