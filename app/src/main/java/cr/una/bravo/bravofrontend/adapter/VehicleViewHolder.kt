package cr.una.bravo.bravofrontend.adapter

import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.VehicleItemBinding
import cr.una.bravo.bravofrontend.model.Vehicle

class VehicleViewHolder (
     val vehicleListItemBinding: VehicleItemBinding
    ):  RecyclerView.ViewHolder(vehicleListItemBinding.root){

        fun bindVehicle(vehicle : Vehicle){

            vehicleListItemBinding.placa.text = vehicle.plateNumber
            vehicleListItemBinding.vinNumber.text = vehicle.vinNumber
            vehicleListItemBinding.brand.text = vehicle.brand
            vehicleListItemBinding.typeVehicle.text = vehicle.vehicleClass
        }


}