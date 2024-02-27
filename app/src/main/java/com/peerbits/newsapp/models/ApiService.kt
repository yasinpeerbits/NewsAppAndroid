package com.peerbits.newsapp.models

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

data class LoginRequest(
    val username: String,
    val password: String
)

val API_KEY = "ab93e3ece3884da08c23c9d4cb02af71"

interface ApiService {
    @Headers("Accept: application/json")
    @POST("auth/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>


    @GET("v2/top-headlines")
    fun getNewsList(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ):Call<NewsResponse>


}