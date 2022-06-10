package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ClientListItemBinding
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.BuildConfig.DATE_FORMAT
import java.text.SimpleDateFormat

class ClientCardViewHolder(
    private val clientListItemBinding: ClientListItemBinding
) : RecyclerView.ViewHolder(clientListItemBinding.root) {
    fun bindClientCard(clientCard: UserBasic) {
        clientListItemBinding.clientId.text = "ID: " + clientCard.id.toString()
        clientListItemBinding.clientName.text =
            "Nombre: " + clientCard.firstName + " " + clientCard.lastName
        clientListItemBinding.clientEmail.text = "Correo: " + clientCard.email
        clientListItemBinding.clientEntryDate.text =
            "Fecha de Ingreso: " + SimpleDateFormat(DATE_FORMAT).format(clientCard.createDate)
    }
}