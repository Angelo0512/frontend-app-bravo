package cr.una.bravo.bravofrontend.viewmodel
/*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cr.una.bravo.bravofrontend.repository.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    loginService = LoginService.getInstance()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/