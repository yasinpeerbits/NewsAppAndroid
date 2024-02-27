//package com.peerbits.newsapp.services
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitClient {
//    //private var BASE_URL = "http://dev2.prep.study/api/"
//    //private var BASE_URL = "https://jsonplaceholder.typicode.com"
//    private var BASE_URL = "https://newsapi.org/"
//    private var SEARCH_NEWS_TIME_DELAY = "500L"
//    private var QUERY_PAGE_SIZE = "20"
//
//    val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//}
//
//object ApiClient {
//    val apiService: ApiService by lazy {
//        RetrofitClient.retrofit.create(ApiService::class.java)
//    }
//}


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
