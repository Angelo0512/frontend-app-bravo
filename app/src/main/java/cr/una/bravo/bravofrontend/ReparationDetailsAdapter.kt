package cr.una.bravo.bravofrontend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ServiceCard
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding
import cr.una.bravo.bravofrontend.databinding.ServiceListItemBinding

class ReparationDetailsAdapter (
    private val serviceCard: MutableList<ServiceCard>,
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