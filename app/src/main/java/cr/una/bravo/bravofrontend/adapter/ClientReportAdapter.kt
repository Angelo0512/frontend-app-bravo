package cr.una.bravo.bravofrontend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.databinding.ClientItemBinding
import cr.una.bravo.bravofrontend.databinding.VehicleItemBinding
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.model.Vehicle

class ClientReportAdapter : RecyclerView.Adapter<ClientViewHolder>(){

    private var user = mutableListOf<UserBasic>()


    fun setUserList(userList: List<UserBasic>) {
        this.user.clear()
        this.user.addAll(userList.toMutableList())
        this.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ClientItemBinding.inflate(from, parent, false)
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bindClient(user[position])
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}