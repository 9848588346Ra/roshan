package com.example.roomease

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomease.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        auth = FirebaseAuth.getInstance()

        loginBinding.butlogin.setOnClickListener {
            val email = loginBinding.username.text.toString().trim()
            val password = loginBinding.password.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        loginBinding.butsignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, Signup::class.java)
            startActivity(intent)
        }

        loginBinding.Forgetpassword.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_LONG).show()
                    navigateToDashboard()
                } else {
                    handleLoginError(task.exception)
                }
            }
    }

    private fun handleLoginError(exception: Exception?) {
        when (exception) {
            is FirebaseAuthInvalidCredentialsException -> {
                Toast.makeText(applicationContext, "Invalid credentials: ${exception.message}", Toast.LENGTH_LONG).show()
            }
            is FirebaseAuthInvalidUserException -> {
                Toast.makeText(applicationContext, "No account found with this email: ${exception.message}", Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(applicationContext, "Authentication failed: ${exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, FlightBooking::class.java)
        startActivity(intent)
        finish()
    }
}
