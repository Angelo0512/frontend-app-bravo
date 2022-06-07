package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import kotlinx.coroutines.*

sealed class StateVehicle {
    object Loading : StateVehicle()
    data class Success(val vehicle: Vehicle?) : StateVehicle()
    data class SuccessDelete(val deleted: Boolean?) : StateVehicle()
    data class SuccessList(val vehicleList: List<Vehicle>?) : StateVehicle()
    data class Error(val message: String) : StateVehicle()
}

class VehicleViewModel constructor(
    private val vehicleRepository: VehicleRepository,
): ViewModel() {
    private val _state = MutableLiveData<StateVehicle>()
    val state: LiveData<StateVehicle> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getVehicle(id: Long) {
        _state.value = StateVehicle.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = vehicleRepository.getVehicleById(id)
            withContext(Dispatchers.Main){
                _state.postValue(
                    if(response.isSuccessful) StateVehicle.Success(response.body())
                    else StateVehicle.Error("Error: ${response.message()}")
                )
            }
        }
    }

    fun findAllVehicles() {
        _state.value = StateVehicle.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = vehicleRepository.getAllVehicles()
            withContext(Dispatchers.Main){
                _state.postValue(
                    if(response.isSuccessful) StateVehicle.SuccessList(response.body())
                    else StateVehicle.Error("Error: ${response.message()}")
                )
            }
        }
    }

    fun createVehicle(vehicleRequest : Vehicle){
        _state.value = StateVehicle.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = vehicleRepository.createVehicle(vehicleRequest)
            withContext(Dispatchers.Main) {
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateVehicle.Success(response.body() as Vehicle)
                    } else {
                        StateVehicle.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateVehicle?
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