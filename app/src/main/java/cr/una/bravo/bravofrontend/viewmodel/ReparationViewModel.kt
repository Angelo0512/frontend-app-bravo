package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.bravo.bravofrontend.model.Report
import cr.una.bravo.bravofrontend.repository.ReportRepository
import kotlinx.coroutines.*

sealed class StateReparation {
    object Loading : StateReparation()
    data class Success(val Report: Report?) : StateReparation()
    data class SuccessDelete(val deleted: Boolean?) : StateReparation()
    data class SuccessList(val reportList: List<Report>?) : StateReparation()
    data class Error(val message: String) : StateReparation()
}

class ReparationViewModel constructor(
    private val reportRepository: ReportRepository,
): ViewModel() {
    private val _state = MutableLiveData<StateReparation>()
    val state: LiveData<StateReparation> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getReparation(id: Long) {
        _state.value = StateReparation.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.getReportById(id)
            withContext(Dispatchers.Main){
                _state.postValue(
                    if(response.isSuccessful) StateReparation.Success(response.body())
                    else StateReparation.Error("Error: ${response.message()}")
                )
            }
        }
    }

    fun findAllReparations() {
        _state.value = StateReparation.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.getAllReports()
            withContext(Dispatchers.Main){
                _state.postValue(
                    if(response.isSuccessful) StateReparation.SuccessList(response.body())
                    else StateReparation.Error("Error: ${response.message()}")
                )
            }
        }
    }

    fun createReparation(reportRequest : Report){
        _state.value = StateReparation.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.createReport(reportRequest)
            withContext(Dispatchers.Main) {
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateReparation.Success(response.body() as Report)
                    } else {
                        StateReparation.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateReparation?
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