package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding
import cr.una.bravo.bravofrontend.model.Report


class ReparationCardViewHolder(
     val reparationsListItemBinding: ReparationsListItemBinding
) : RecyclerView.ViewHolder(reparationsListItemBinding.root) {
    fun bindReparationCard(repCard: Report) {
        reparationsListItemBinding.reparationId.text = repCard.id.toString()
        reparationsListItemBinding.clientId.text = repCard.client.id.toString()
        reparationsListItemBinding.clientName.text = repCard.client.firstName + " " + repCard.client.lastName
        reparationsListItemBinding.vehiclePlate.text = repCard.vehicle.plateNumber
    }
}