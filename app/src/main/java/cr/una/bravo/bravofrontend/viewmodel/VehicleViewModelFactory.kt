package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import cr.una.bravo.bravofrontend.service.VehicleService

@Suppress("UNCHECKED_CAST")
class VehicleViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(VehicleViewModel::class.java)){
            VehicleViewModel(
                vehicleRepository = VehicleRepository(
                    vehicleService =  VehicleService.getInstance()
                )
            ) as T
        } else {
            throw IllegalAccessException("ViewModel Not Found")
        }
    }
}