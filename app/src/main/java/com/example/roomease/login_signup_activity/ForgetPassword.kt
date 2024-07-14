package com.example.roomease

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomease.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.changePassword.setOnClickListener {
            val currentPassword = binding.currentPassword.text.toString().trim()
            val newPassword = binding.newpassword.text.toString().trim()
            val confirmNewPassword = binding.newconformpassword.text.toString().trim()

            if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (newPassword != confirmNewPassword) {
                Toast.makeText(applicationContext, "New passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                changePassword(currentPassword, newPassword)
            }
        }

        binding.back.setOnClickListener {
            navigateToLoginActivity()
        }
    }

    private fun changePassword(currentPassword: String, newPassword: String) {
        val user = auth.currentUser
        if (user != null && user.email != null) {
            val credential = EmailAuthProvider.getCredential(user.email!!, currentPassword)
            user.reauthenticate(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user.updatePassword(newPassword).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            sendEmailVerification()
                        } else {
                            Toast.makeText(applicationContext, "Failed to change password: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(applicationContext, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(applicationContext, "No authenticated user found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "Password changed successfully. Please verify your email.", Toast.LENGTH_SHORT).show()
                navigateToLoginActivity()
            } else {
                Toast.makeText(applicationContext, "Failed to send verification email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
