package com.ceren.cerenapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_fragment)

        credentialsManager = CredentialsManager()

        findViewById<TextView>(R.id.login).setOnClickListener {
            Log.d("RegisterActivity", "Login button clicked")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.registerButton).setOnClickListener {
            val nameInput = findViewById<TextInputEditText>(R.id.full_name)?.text.toString()
            val emailInput = findViewById<TextInputEditText>(R.id.email_address)?.text.toString()
            val passwordInput = findViewById<TextInputEditText>(R.id.password)?.text.toString()

            if (validateRegistration(nameInput, emailInput, passwordInput)) {
                Log.d("RegisterActivity", "Registration successful")
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun validateRegistration(name: String, email: String, password: String): Boolean {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (name.length < 3) {
            Toast.makeText(this, "Name must be at least 3 characters", Toast.LENGTH_SHORT).show()
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