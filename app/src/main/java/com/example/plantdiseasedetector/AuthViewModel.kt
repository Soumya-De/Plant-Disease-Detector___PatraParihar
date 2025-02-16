package com.example.plantdiseasedetector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    fun login(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.instance.login(AuthRequest(email, password))
                if (response.isSuccessful) {
                    onResult(true, "Login successful")
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("AuthError", "Error: $errorBody")
                    onResult(false, "User does not exist or incorrect credentials")
                }
            } catch (e: Exception) {
                onResult(false, "Connection error: ${e.message}")
            }
        }
    }


    fun register(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.instance.register(AuthRequest(email, password))
                if (response.isSuccessful) {
                    onResult(true, "Registration successful")
                } else {
                    onResult(false, response.errorBody()?.string() ?: "Unknown error")
                }
            } catch (e: Exception) {
                onResult(false, "Connection error: ${e.message}")
            }
        }
    }

    fun logout(navController: NavHostController) {
        viewModelScope.launch {
            try {
                val response = ApiClient.instance.logout()
                if (response.isSuccessful) {
                    ApiClient.clearSession()
                    navController.navigate("login") {
                        popUpTo("main") { inclusive = true }
                        //launchSingleTop = true
                    }
                } else {
                    println("Logout failed: ${response.message()}")
                }
            } catch (e: Exception) {
                println("Logout error: ${e.message}")
            }
        }
    }

}
