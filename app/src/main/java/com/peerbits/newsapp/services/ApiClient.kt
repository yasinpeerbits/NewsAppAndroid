package com.peerbits.newsapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    fun createApiService(baseUrl: String): ApiService {
        val retrofit = RetrofitClient.createRetrofit(baseUrl)
        return retrofit.create(ApiService::class.java)
    }
}
