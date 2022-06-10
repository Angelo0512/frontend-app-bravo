package cr.una.bravo.bravofrontend.adapter

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ClientItemBinding
import cr.una.bravo.bravofrontend.databinding.VehicleItemBinding
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.model.Vehicle

class ClientViewHolder(

    val clientListItemBinding: ClientItemBinding
): RecyclerView.ViewHolder(clientListItemBinding.root){

    fun bindClient(user : UserBasic){

        clientListItemBinding.IdClient.text = user.id.toString()
        clientListItemBinding.NameClien.text = user.firstName + " " + user.lastName
        clientListItemBinding.EmailCliente.text = user.email

    }


}

