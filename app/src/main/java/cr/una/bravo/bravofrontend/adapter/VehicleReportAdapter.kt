package cr.una.bravo.bravofrontend.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        /*
        if (clickedPosition == position)
            holder.vehicleListItemBinding.root.setBackgroundColor(Color.parseColor("#000000"))
        else
            holder.vehicleListItemBinding.root.setBackgroundColor(Color.parseColor("#ffffff"))
*/

    }

    override fun getItemCount(): Int = vehicle.size
}