package cr.una.bravo.bravofrontend.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.VehicleListItemBinding
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.viewmodel.VehicleCardViewHolder


class VehicleCardAdapter(
    private val vehicleCards: MutableList<Vehicle>
) : RecyclerView.Adapter<VehicleCardViewHolder>() {

    private var vehicleCardsOriginal: MutableList<Vehicle> = vehicleCards
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleCardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = VehicleListItemBinding.inflate(from, parent, false)
        return VehicleCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleCardViewHolder, position: Int) {
        holder.bindVehicleCard(vehicleCards[position])
        holder.vehicleListItemBinding.root.setOnClickListener {
            val toast = Toast.makeText(it.context, "Click", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    override fun getItemCount(): Int = vehicleCards.size

    @SuppressLint("NotifyDataSetChanged")
    fun filter(SearchText: String, spinnerFilter: String) {
        lateinit var newList: MutableList<Vehicle>
        /*
        if (spinnerFilter == "Cliente")
            newList = vehicleCardsOriginal.filter {
                it.clienteName.replace(
                    "Cliente: ",
                    ""
                ) == SearchText
            } as MutableList<Vehicle>

         */
        if (spinnerFilter == "Placa")
            newList = vehicleCardsOriginal.filter {
                it.plateNumber == SearchText
            } as MutableList<Vehicle>
        vehicleCards.clear()
        vehicleCards.addAll(newList)
        for (r in vehicleCards)
            notifyDataSetChanged()
    }
}