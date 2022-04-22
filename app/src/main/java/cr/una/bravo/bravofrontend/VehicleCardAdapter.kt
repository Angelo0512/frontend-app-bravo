package cr.una.bravo.bravofrontend

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.VehicleCard
import cr.una.bravo.bravofrontend.databinding.VehicleListItemBinding


class VehicleCardAdapter(
    private val vehicleCards: MutableList<VehicleCard>
) : RecyclerView.Adapter<VehicleCardViewHolder>() {

    private var vehicleCardsOriginal: MutableList<VehicleCard> = vehicleCards
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
        lateinit var newList: MutableList<VehicleCard>
        if (spinnerFilter == "Cliente")
            newList = vehicleCardsOriginal.filter {
                it.clienteName.replace(
                    "Cliente: ",
                    ""
                ) == SearchText
            } as MutableList<VehicleCard>
        if (spinnerFilter == "Placa")
            newList = vehicleCardsOriginal.filter {
                it.vehiclePlate.replace(
                    "Placa: ",
                    ""
                ) == SearchText
            } as MutableList<VehicleCard>
        vehicleCards.clear()
        vehicleCards.addAll(newList)
        for (r in vehicleCards)
            notifyDataSetChanged()
    }
}