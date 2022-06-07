package com.pelsinkaplan.firebaseemailpasswordauthsampleproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.pelsinkaplan.firebaseemailpasswordauthsampleproject.databinding.ActivityLoginBinding
import com.pelsinkaplan.firebaseemailpasswordauthsampleproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            registerButton.setOnClickListener {
                registerValidation(
                    emailEdittext.text.toString(),
                    passwordEdittext.text.toString(),
                    passwordAgainEdittext.text.toString()
                )
            }
        }
    }

    fun registerValidation(email: String, password: String, repassword: String) {

        val emailControl = email.trim() { it <= ' ' }
        val passwordControl = password.trim() { it <= ' ' }
        val rePasswordControl = repassword.trim() { it <= ' ' }

        if (passwordControl != rePasswordControl)
            Toast.makeText(
                this,
                "Passwords do not match!",
                Toast.LENGTH_SHORT
            ).show()
        else {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(emailControl, passwordControl)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Registration Successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else
                        Toast.makeText(
                            this,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                }
        }
    }
}