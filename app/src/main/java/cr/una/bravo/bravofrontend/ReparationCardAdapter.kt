package cr.una.bravo.bravofrontend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.data.model.ReparationCard
import cr.una.bravo.bravofrontend.databinding.ReparationsListItemBinding

class ReparationCardAdapter (private val reparationCards: List<ReparationCard>):
 RecyclerView.Adapter<ReparationCardViewHolder>(){
 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReparationCardViewHolder {
  val from = LayoutInflater.from(parent.context)
  val binding = ReparationsListItemBinding.inflate(from,parent,false)
  return ReparationCardViewHolder(binding)
 }

 override fun onBindViewHolder(holder: ReparationCardViewHolder, position: Int) {
  holder.bindReparationCard(reparationCards[position])
 }

 override fun getItemCount(): Int = reparationCards.size
}