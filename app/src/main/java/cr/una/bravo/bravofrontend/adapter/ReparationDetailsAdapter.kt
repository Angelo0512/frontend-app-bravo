package cr.una.bravo.bravofrontend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ServiceListItemBinding
import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.viewmodel.ReparationDetailsCardViewHolder

class ReparationDetailsAdapter (
    private val serviceCard: MutableList<Service>,
):
    RecyclerView.Adapter<ReparationDetailsCardViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReparationDetailsCardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ServiceListItemBinding.inflate(from,parent,false)
        return ReparationDetailsCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReparationDetailsCardViewHolder, position: Int) {
        holder.bindReparationCard(serviceCard[position])
    }

    override fun getItemCount(): Int = serviceCard.size


}