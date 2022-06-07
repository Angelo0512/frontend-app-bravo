package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.bravo.bravofrontend.model.Vehicle
import cr.una.bravo.bravofrontend.repository.VehicleRepository
import kotlinx.coroutines.*

class VehicleViewModel constructor(
    private val vehicleRepository: VehicleRepository,
): ViewModel() {
    val vehicle = MutableLiveData<Vehicle>()
    val vehicleList = MutableLiveData<List<Vehicle>>()

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getVehicle(id: Long) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = vehicleRepository.getVehicleById(id)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    vehicle.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    fun findAllVehicles() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = vehicleRepository.getAllVehicles()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    vehicleList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
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