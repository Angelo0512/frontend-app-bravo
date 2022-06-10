package cr.una.bravo.bravofrontend.viewmodel

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding
import cr.una.bravo.bravofrontend.model.Report


class ReparationCardViewHolder(
     val reparationsListItemBinding: ReparationsListItemBinding
) : RecyclerView.ViewHolder(reparationsListItemBinding.root) {
    @SuppressLint("SetTextI18n")
    fun bindReparationCard(repCard: Report) {
        reparationsListItemBinding.reparationId.text = "ID: " + repCard.id.toString()
        reparationsListItemBinding.clientId.text = "ID del Cliente: " + repCard.client.id.toString()
        reparationsListItemBinding.clientName.text = "Nombre del Cliente: " + repCard.client.firstName + " " + repCard.client.lastName
        reparationsListItemBinding.vehiclePlate.text = "Placa del Vehículo: " + repCard.vehicle.plateNumber
    }
}