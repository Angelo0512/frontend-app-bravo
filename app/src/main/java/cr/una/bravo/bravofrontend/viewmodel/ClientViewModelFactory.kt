package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cr.una.bravo.bravofrontend.repository.UserRepository
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import cr.una.bravo.bravofrontend.service.UserService
import cr.una.bravo.bravofrontend.service.VehicleService

@Suppress("UNCHECKED_CAST")
class ClientViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ClientViewModel::class.java)){
            ClientViewModel(
                userRepository = UserRepository(
                    userService =  UserService.getInstance()
                )
            ) as T
        } else {
            throw IllegalAccessException("ViewModel Not Found")
        }
    }
}