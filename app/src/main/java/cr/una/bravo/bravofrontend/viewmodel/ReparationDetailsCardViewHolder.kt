package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding
import cr.una.bravo.bravofrontend.databinding.ServiceListItemBinding
import cr.una.bravo.bravofrontend.model.Service

class ReparationDetailsCardViewHolder(
    private val serviceListItemBinding: ServiceListItemBinding
) : RecyclerView.ViewHolder(serviceListItemBinding.root) {
    fun bindReparationCard(repCard: Service){
        serviceListItemBinding.servicioId.text = "ID: " + repCard.id.toString()
        serviceListItemBinding.servicioName.text = "Nombre: " + repCard.name
        serviceListItemBinding.observations.text = "Observaciones: " + repCard.observations
        serviceListItemBinding.state.text = "Estado: " + repCard.state

    }
}