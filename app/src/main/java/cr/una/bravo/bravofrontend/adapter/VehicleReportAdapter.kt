package cr.una.bravo.bravofrontend.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.VehicleItemBinding
import cr.una.bravo.bravofrontend.model.Vehicle

class VehicleReportAdapter : RecyclerView.Adapter<VehicleViewHolder>() {

    private var vehicle = mutableListOf<Vehicle>()
    private val clickedPosition = -1
    private val selectedItem = 0

    fun setVehicleList(vehicleList: List<Vehicle>) {
        this.vehicle.clear()
        this.vehicle.addAll(vehicleList.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = VehicleItemBinding.inflate(from, parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bindVehicle(vehicle[position])
        holder.vehicleListItemBinding.root.setOnClickListener{
            val bundle = bundleOf(VEHICLE_ID to vehicle[position].id.toString())
            holder.vehicleListItemBinding.root.findNavController().navigate(
                R.id.action_insertVehicle_to_insertClientFragment, bundle
            )
        }

    }
    companion object {
        const val VEHICLE_ID = "vehicle_id"
    }
    override fun getItemCount(): Int = vehicle.size
}