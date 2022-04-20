package cr.una.bravo.bravofrontend.ui.login

/**
 * Data validation state of the login_button form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)