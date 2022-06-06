package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.VehicleCard
import cr.una.bravo.bravofrontend.databinding.VehicleListItemBinding

class VehicleCardViewHolder(
    val vehicleListItemBinding: VehicleListItemBinding
) : RecyclerView.ViewHolder(vehicleListItemBinding.root){
    fun bindVehicleCard(vehCard : VehicleCard){
        vehicleListItemBinding.vehiclePlate.text = vehCard.vehiclePlate
        vehicleListItemBinding.clientName.text = vehCard.clienteName
        vehicleListItemBinding.vehicleBrand.text = vehCard.vehicleBrand
        vehicleListItemBinding.vehicleType.text = vehCard.vehicleType
    }
}