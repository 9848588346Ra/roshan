package com.example.roomease

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomease.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

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

            loginUser(email, password)
        }

        // Navigate to SignupActivity when signupButton is clicked
        loginBinding.butsignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, Signup::class.java)
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
                    Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun navigateToDashboard() {
        val intent = Intent(this,DashboardActivity::class.java)
        startActivity(intent)
        finish()  // Optional: finish current activity if you don't want to go back to it
    }
}
