package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.bravo.bravofrontend.model.Service
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.repository.ServiceRepository
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import kotlinx.coroutines.*

sealed class StateService {
    object Loading : StateService()
    data class Success(val service: Service?) : StateService()
    data class SuccessDelete(val deleted: Boolean?) : StateService()
    data class SuccessList(val serviceList: List<Service>?) : StateService()
    data class Error(val message: String) : StateService()
}

class ServiceViewModel constructor(
    private val serviceRepository: ServiceRepository,
): ViewModel() {
    private val _state = MutableLiveData<StateService>()
    val state: LiveData<StateService> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getService(id: Long) {
        _state.value = StateService.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = serviceRepository.getServiceById(id)
            withContext(Dispatchers.Main){
                _state.postValue(
                    if(response.isSuccessful) StateService.Success(response.body())
                    else StateService.Error("Error: ${response.message()}")
                )
            }
        }
    }

    fun findAllServices() {
        _state.value = StateService.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = serviceRepository.getAllServices()
            withContext(Dispatchers.Main){
                _state.postValue(
                    if(response.isSuccessful) StateService.SuccessList(response.body())
                    else StateService.Error("Error: ${response.message()}")
                )
            }
        }
    }

    fun createService(serviceRequest : Service){
        _state.value = StateService.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = serviceRepository.createService(serviceRequest)
            withContext(Dispatchers.Main) {
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateService.Success(response.body() as Service)
                    } else {
                        StateService.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateService?
                )
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}