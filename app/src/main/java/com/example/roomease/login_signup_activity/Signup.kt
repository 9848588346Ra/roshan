package com.example.roomease

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roomease.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth


class Signup : AppCompatActivity() {
    private lateinit var signupBinding: ActivitySignupBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        signupBinding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)
        auth = FirebaseAuth.getInstance()

        signupBinding.signupbutton.setOnClickListener{
            val Email = signupBinding.emailsignup.text.toString().trim()
            val password = signupBinding.passwordsignup.text.toString().trim()

//            if (Email.isEmpty() && password.isEmpty()){
//
//            }

            signupWithFirebase(Email,password)


        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun signupWithFirebase(email: String, password: String
    ){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                task->
            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Account created successfully",Toast.LENGTH_LONG).show()

                val intent = Intent(this@Signup, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(applicationContext,"Failed to create account" ,Toast.LENGTH_LONG).show()
            }

        }
    }
}