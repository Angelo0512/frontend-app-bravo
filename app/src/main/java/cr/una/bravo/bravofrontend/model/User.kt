package cr.una.bravo.bravofrontend.model

import java.util.*

data class UserLogin(
    var email: String,
    var password: String,
)

data class User(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var enabled: Boolean,
    var tokenExpired: Boolean,
    var createDate: Date,
    var roles: List<Roles>,
)

/**
 * User details post authentication that is exposed to the UI
 */
data class UserBasic(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var email: String,
    var createDate: Date,
)

data class Roles(
    var id: Long,
    var name: String,
    var privilege: List<Privilege>,
)

data class Privilege(
    var id: Long,
    var name: String,
)

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)

data class LoginRequest(
    var username: String,
    var password: String,
)

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val username: String,
    var authorities: List<Authority>?,
    //... other data fields that may be accessible to the UI
)

data class UserLoginResponse(
    var username: String,
    var password: String,
    var authorities: List<Authority>,
    var accountNonExpired: Boolean,
    var accountNonLocked: Boolean,
    var credentialsNonExpired: Boolean,
    var enabled: Boolean,
)

data class Authority(
    var authority: String,
)