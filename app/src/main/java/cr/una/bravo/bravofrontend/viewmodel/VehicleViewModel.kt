package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.model.VehicleProvider

class VehicleViewModel : ViewModel() {
    val vehicle = MutableLiveData<Vehicle>()
    val vehicleList = MutableLiveData<List<Vehicle>>()

    fun getTask() {
        val position = (0..3).random()
        val _vehicle = VehicleProvider.findVehicleById(position)
        vehicle.postValue(_vehicle)
    }

    fun findAllTask() {
        val _vehicleList = VehicleProvider.findAllVehicles()
        vehicleList.postValue(_vehicleList)
    }
}