package cr.una.bravo.bravofrontend.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ServiceListItemBinding
import cr.una.bravo.bravofrontend.model.Report
import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.viewmodel.ReparationDetailsCardViewHolder

class ReparationDetailsAdapter :
    RecyclerView.Adapter<ReparationDetailsCardViewHolder>()
{
    private var serviceCards = mutableListOf<Service>()


    @SuppressLint("NotifyDataSetChanged")
    fun setServiceList(service : List<Service>){
        this.serviceCards = service.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReparationDetailsCardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ServiceListItemBinding.inflate(from,parent,false)
        return ReparationDetailsCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReparationDetailsCardViewHolder, position: Int) {
        holder.bindReparationCard(serviceCards[position])
    }

    override fun getItemCount(): Int = serviceCards.size


}