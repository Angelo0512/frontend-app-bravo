package cr.una.bravo.bravofrontend.viewmodel

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.VehicleListItemBinding
import cr.una.bravo.bravofrontend.model.Vehicle

class VehicleCardViewHolder(
    val vehicleListItemBinding: VehicleListItemBinding
) : RecyclerView.ViewHolder(vehicleListItemBinding.root){
    fun bindVehicleCard(vehCard : Vehicle){
        vehicleListItemBinding.vehiclePlate.text = "Placa: " + vehCard.plateNumber
        vehicleListItemBinding.vehicleVIN.text = "VIN: " + vehCard.vinNumber
        vehicleListItemBinding.vehicleBrand.text = "Marca: " + vehCard.brand
        vehicleListItemBinding.vehicleMotorSerial.text = "Serial del Motor: " + vehCard.motorSerial
        vehicleListItemBinding.vehicleType.text = "Tipo: " + vehCard.vehicleClass
        vehicleListItemBinding.vehicleMotorType.text = "Tipo de Motor: " + vehCard.motorType
    }
}