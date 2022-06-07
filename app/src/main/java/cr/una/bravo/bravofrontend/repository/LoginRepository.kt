package cr.una.bravo.bravofrontend.repository
/*
class LoginRepository constructor (
    private val loginService: LoginService
){

    // in-memory cache of the loggedInUser object
    private var user: UserLoginResponse? = null

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        sessionManager?.deleteAuthToken()
    }

    suspend fun login(userLogin: LoginRequest)  : Response<UserLoginResponse> {
        val response = loginService.login(userLogin)

        if (response.isSuccessful) {
            setLoggedInUser(response.body(), response.headers()["Authorization"].toString())
        }

        return response
    }

    private fun setLoggedInUser(loginRequest: UserLoginResponse?, token:String) {
        this.user = loginRequest
        sessionManager?.saveAuthToken(token)

        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}*/