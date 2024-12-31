package com.ceren.cerenapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_fragment)

        credentialsManager = CredentialsManager()

        findViewById<TextView>(R.id.register).setOnClickListener {
            Log.d("LoginActivity", "Register button clicked")
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val emailInput = findViewById<TextInputEditText>(R.id.emailInputLayout)?.text.toString()
            val passwordInput = findViewById<TextInputEditText>(R.id.passwordInputLayout)?.text.toString()

            if (validateCredentials(emailInput, passwordInput)) {
                Log.d("LoginActivity", "Login successful")
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password fields cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!credentialsManager.isEmailValid(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!credentialsManager.isPasswordValid(password)) {
            Toast.makeText(this, "Password must be at least 8 characters and contain uppercase, lowercase, number and special character", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}