package cr.una.bravo.bravofrontend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.ClientItemBinding
import cr.una.bravo.bravofrontend.databinding.VehicleItemBinding
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.model.Vehicle

class ClientReportAdapter : RecyclerView.Adapter<ClientViewHolder>(){

    private var user = mutableListOf<UserBasic>()
    private val clickedPosition = -1
    private val selectedItem = 0


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
        holder.clientListItemBinding.root.setOnClickListener {
            val bundle = bundleOf(USER_ID to user[position].id.toString())
            holder.clientListItemBinding.root.findNavController().navigate(
                R.id.action_insertClientFragment_to_insertServiceFragment, bundle
            )
        }
    }

    companion object {
        const val USER_ID = "user_id"
    }
    override fun getItemCount(): Int = user.size
}