package com.ceren.cerenapplication

class CredentialsManager {
    fun isEmailValid(email: String): Boolean {
        val emailRegex = ("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return email.matches(emailRegex.toRegex())
    }

    fun isPasswordValid(password: String): Boolean {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        val isLongEnough = password.length >= 8

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar && isLongEnough
    }
}