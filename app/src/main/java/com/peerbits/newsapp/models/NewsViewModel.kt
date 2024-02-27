package com.peerbits.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peerbits.newsapp.models.Article
import com.peerbits.newsapp.models.NewsResponse
import com.peerbits.newsapp.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>> get() = _newsList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchNews()
    }

    private fun fetchNews() {
        _isLoading.value = true
        ApiClient.createApiService("https://newsapi.org/").getNewsList()
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        _newsList.value = response.body()?.articles ?: emptyList()
                    }
                    _isLoading.value = false
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    _isLoading.value = false
                }
            })
    }
}
