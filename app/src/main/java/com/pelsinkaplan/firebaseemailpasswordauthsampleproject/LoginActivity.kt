package com.pelsinkaplan.firebaseemailpasswordauthsampleproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.pelsinkaplan.firebaseemailpasswordauthsampleproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            registerButton.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
            loginButton.setOnClickListener {
                loginValidation(
                    emailEdittext.text.toString(),
                    passwordEdittext.text.toString()
                )
            }
        }
    }

    fun loginValidation(email: String, password: String) {
        val emailControl = email.trim { it <= ' ' }
        val passwordControl = password.trim { it <= ' ' }

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(emailControl, passwordControl)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Login Successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                } else
                    Toast.makeText(
                        this,
                        task.exception!!.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
            }
    }
}