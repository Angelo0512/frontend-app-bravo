package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.VehicleListItemBinding
import cr.una.bravo.bravofrontend.model.Vehicle

class VehicleCardViewHolder(
    val vehicleListItemBinding: VehicleListItemBinding
) : RecyclerView.ViewHolder(vehicleListItemBinding.root){
    fun bindVehicleCard(vehCard : Vehicle){
        vehicleListItemBinding.vehiclePlate.text = vehCard.plateNumber
        vehicleListItemBinding.vehicleBrand.text = vehCard.brand
        vehicleListItemBinding.vehicleType.text = vehCard.vehicleClass
    }
}