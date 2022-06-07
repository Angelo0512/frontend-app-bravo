package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.bravo.bravofrontend.model.ClientProvider
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.model.VehicleProvider

class ClientViewModel : ViewModel() {
    val client = MutableLiveData<UserBasic>()
    val clientList = MutableLiveData<List<UserBasic>>()

    fun getTask() {
        val position = (0..3).random()
        val _client = ClientProvider.findClientById(position)
        client.postValue(_client)
    }

    fun findAllTask() {
        val _clientList = ClientProvider.findAllClients()
        clientList.postValue(_clientList)
    }
}