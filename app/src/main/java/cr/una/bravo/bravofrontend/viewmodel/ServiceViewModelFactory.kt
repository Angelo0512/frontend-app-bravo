package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cr.una.bravo.bravofrontend.repository.ServiceRepository
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import cr.una.bravo.bravofrontend.service.ServiceService
import cr.una.bravo.bravofrontend.service.VehicleService

@Suppress("UNCHECKED_CAST")
class ServiceViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ServiceViewModel::class.java)){
            ServiceViewModel(
                serviceRepository = ServiceRepository(
                    serviceService =  ServiceService.getInstance()
                )
            ) as T
        } else {
            throw IllegalAccessException("ViewModel Not Found")
        }
    }
}