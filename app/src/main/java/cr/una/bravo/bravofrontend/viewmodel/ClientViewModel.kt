package cr.una.bravo.bravofrontend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.bravo.bravofrontend.model.User
import cr.una.bravo.bravofrontend.model.UserBasic
import cr.una.bravo.bravofrontend.repository.UserRepository
import kotlinx.coroutines.*

sealed class StateClient {
    object Loading : StateClient()
    data class SuccessUser(val user: User?) : StateClient()
    data class SuccessUserBasic(val user: UserBasic?) : StateClient()
    data class SuccessDelete(val deleted: Boolean?) : StateClient()
    data class SuccessList(val userList: List<UserBasic>?) : StateClient()
    data class Error(val message: String) : StateClient()
}

class ClientViewModel constructor(
    private val userRepository: UserRepository,
): ViewModel() {
    private val _state = MutableLiveData<StateClient>()
    val state: LiveData<StateClient> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getUser(id: Long) {
        _state.value = StateClient.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = userRepository.getClientById(id)
            withContext(Dispatchers.Main){
                _state.postValue(
                    if(response.isSuccessful) StateClient.SuccessUserBasic(response.body())
                    else StateClient.Error("Error: ${response.message()}")
                )
            }
        }
    }

    fun findAllUsers() {
        _state.value = StateClient.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = userRepository.getAllClients()
            withContext(Dispatchers.Main){
                _state.postValue(
                    if(response.isSuccessful) StateClient.SuccessList(response.body())
                    else StateClient.Error("Error: ${response.message()}")
                )
            }
        }
    }



    fun createUser(userRequest : User){
        _state.value = StateClient.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = userRepository.createClient(userRequest)
            withContext(Dispatchers.Main) {
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateClient.SuccessUser(response.body() as User)
                    } else {
                        StateClient.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateClient?
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