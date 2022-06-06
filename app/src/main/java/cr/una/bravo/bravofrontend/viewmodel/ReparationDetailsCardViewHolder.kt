package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ReparationCard
import cr.una.bravo.bravofrontend.data.model.ServiceCard
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding
import cr.una.bravo.bravofrontend.databinding.ServiceListItemBinding

class ReparationDetailsCardViewHolder(
    private val serviceListItemBinding: ServiceListItemBinding
) : RecyclerView.ViewHolder(serviceListItemBinding.root) {
    fun bindReparationCard(repCard: ServiceCard){
        serviceListItemBinding.servicioId.text = repCard.serviceId
        serviceListItemBinding.observations.text = repCard.observation
        serviceListItemBinding.state.text = repCard.state

    }
}