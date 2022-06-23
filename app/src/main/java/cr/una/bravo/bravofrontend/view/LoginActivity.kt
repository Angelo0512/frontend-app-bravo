package cr.una.bravo.bravofrontend.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cr.una.bravo.bravofrontend.R
import cr.una.bravo.bravofrontend.databinding.ActivityLoginBinding
import cr.una.bravo.bravofrontend.model.LoggedInUserView
import cr.una.bravo.bravofrontend.model.LoginRequest
import cr.una.bravo.bravofrontend.viewmodel.login.LoginViewModel
import cr.una.bravo.bravofrontend.viewmodel.login.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // With View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_login)

        // LoginViewModelFactory
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.loginButton.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.loginUsername.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.loginPwd.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResponse.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            binding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
                //Complete and destroy login activity once successful
                finish()
            }
            setResult(Activity.RESULT_OK)


        })
        binding.loginUsername.doAfterTextChanged {
            loginViewModel.loginDataChanged(
                LoginRequest(
                    email = binding.loginUsername.text.toString(),
                    password = binding.loginPwd.text.toString()
                )
            )
        }

        binding.loginPwd.apply {
            doAfterTextChanged {
                loginViewModel.loginDataChanged(
                    LoginRequest(
                        email = binding.loginUsername.text.toString(),
                        password = binding.loginPwd.text.toString()
                    )
                )
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            LoginRequest(
                                email = binding.loginUsername.text.toString(),
                                password = binding.loginPwd.text.toString()
                            )
                        )
                }
                false
            }

            binding.loginButton.setOnClickListener {
                binding.loading.visibility = View.VISIBLE
                loginViewModel.login(
                    LoginRequest(
                        email = binding.loginUsername.text.toString(),
                        password = binding.loginPwd.text.toString()
                    )
                )
            }

            loginViewModel.loginDataChanged(
                LoginRequest(
                    email = binding.loginUsername.text.toString(),
                    password = binding.loginPwd.text.toString()
                )
            )
        }
    }

    /**
     * Success login to redirect the app to the next Screen
     */
    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val username = model.username

        // Initiate successful logged in experience
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        Toast.makeText(
            applicationContext,
            "$welcome $username",
            Toast.LENGTH_LONG
        ).show()
    }

    /**
     * Unsuccessful login
     */
    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}