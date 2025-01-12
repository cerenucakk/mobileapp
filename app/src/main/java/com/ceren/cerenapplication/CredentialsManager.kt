package com.ceren.cerenapplication

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class CredentialsManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "UserPrefs", Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return email.matches(emailRegex.toRegex())
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    fun registerUser(name: String, email: String, password: String, callback: (Boolean) -> Unit) {
        if (getUserByEmail(email) != null) {
            callback(false)
            return
        }

        val user = UserData(name, email, password)
        val userJson = gson.toJson(user)
        sharedPreferences.edit()
            .putString(email, userJson)
            .apply()
        
        callback(true)
    }

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        val user = getUserByEmail(email)
        if (user != null && user.password == password) {
            saveLoggedInUser(user)
            callback(true)
        } else {
            callback(false)
        }
    }

    private fun saveLoggedInUser(user: UserData) {
        sharedPreferences.edit()
            .putString("logged_in_user", gson.toJson(user))
            .apply()
    }

    fun getLoggedInUser(): UserData? {
        val userJson = sharedPreferences.getString("logged_in_user", null)
        return if (userJson != null) {
            gson.fromJson(userJson, UserData::class.java)
        } else {
            null
        }
    }

    private fun getUserByEmail(email: String): UserData? {
        val userJson = sharedPreferences.getString(email, null)
        return if (userJson != null) {
            gson.fromJson(userJson, UserData::class.java)
        } else {
            null
        }
    }

    fun logout() {
        sharedPreferences.edit()
            .remove("logged_in_user")
            .apply()
    }
}