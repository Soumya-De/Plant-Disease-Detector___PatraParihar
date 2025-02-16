package com.example.plantdiseasedetector

// ApiService.kt
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST("api/register")
    suspend fun register(@Body request: AuthRequest): Response<AuthResponse>

    @POST("api/logout")
    suspend fun logout(): Response<AuthResponse>
}

// Data classes for requests/responses
data class AuthRequest(
    val email: String,  // Changed from username
    val password: String
)

data class AuthResponse(
    val message: String,
    val user_id: Int? = null
)