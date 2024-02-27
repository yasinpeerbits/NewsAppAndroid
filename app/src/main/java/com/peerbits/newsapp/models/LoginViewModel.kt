package com.peerbits.newsapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peerbits.newsapp.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    // Add LiveData for two-way data binding
    var userName = ""
    var password = ""

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _loginResult = MutableLiveData<Boolean>()
    private val _emptyCred = MutableLiveData<Boolean>()

    val loginResult: LiveData<Boolean>
        get() = _loginResult

    val emptyCred: LiveData<Boolean>
        get() = _emptyCred

    fun loginUser() {

        if (userName.isNotEmpty() && password.isNotEmpty()) {
            _loading.value = true

            val loginRequest = LoginRequest(userName, password)
            val call =
                ApiClient.createApiService("http://dev2.prep.study/api/").loginUser(loginRequest)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    _loading.value = false

                    if (response.body()?.status == "SUCCESS") {
                        _loginResult.value = true
                        _emptyCred.value = false

                    } else {
                        _emptyCred.value = false
                        _loginResult.value = false
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _emptyCred.value = false
                    _loading.value = false
                    _loginResult.value = false
                }
            })
        } else {
            _loading.value = false
            _loginResult.value = false
            _emptyCred.value = true
        }
    }
}
