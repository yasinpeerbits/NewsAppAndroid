package com.peerbits.newsapp.models

import androidx.lifecycle.ViewModel
import com.peerbits.newsapp.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    fun loginUser(
        userName: String,
        password: String,
        onLoginSuccess: () -> Unit,
        onLoginFailure: () -> Unit
    ) {
        val loginRequest = LoginRequest(userName, password)
        val call = ApiClient.createApiService("http://dev2.prep.study/api/").loginUser(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.body()?.status == "SUCCESS") {
                    onLoginSuccess.invoke()
                } else {
                    onLoginFailure.invoke()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onLoginFailure.invoke()
            }
        })
    }
}