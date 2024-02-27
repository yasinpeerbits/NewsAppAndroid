package com.peerbits.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.peerbits.newsapp.adapter.NewsItemClicked
import com.peerbits.newsapp.adapter.NewsListAdapter
import com.peerbits.newsapp.databinding.ActivityNewsListBinding
import com.peerbits.newsapp.models.Article
import com.peerbits.newsapp.models.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var binding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.VISIBLE
        fetchApiData()
    }


    override fun onItemClicked(item: Article) {
        val intent = Intent(this@NewsListActivity, NewDetails::class.java)
        Log.d("This is article", item.toString())
        intent.putExtra("URL", item.url)
        startActivity(intent)
    }


    private fun fetchApiData() {


        val call = ApiClient.apiService.getNewsList();
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsResponse: NewsResponse? = response.body()
                    val newsAdapter = newsResponse?.articles?.let {
                        NewsListAdapter(
                            this@NewsListActivity,
                            it,
                            this@NewsListActivity
                        )
                    }
                    binding.recyclerView.adapter = newsAdapter
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@NewsListActivity)
                    binding.progressBar.visibility = View.GONE

                } else {
                    Log.d("This is response else", response.toString())
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("This is response error", t.toString())
                binding.progressBar.visibility = View.GONE
            }
        })
    }
}