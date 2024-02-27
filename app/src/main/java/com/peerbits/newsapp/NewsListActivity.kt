//package com.peerbits.newsapp
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.peerbits.newsapp.adapter.NewsItemClicked
//import com.peerbits.newsapp.adapter.NewsListAdapter
//import com.peerbits.newsapp.databinding.ActivityNewsListBinding
//import com.peerbits.newsapp.models.Article
//import com.peerbits.newsapp.models.NewsResponse
//import com.peerbits.newsapp.services.ApiClient
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class NewsListActivity : AppCompatActivity(), NewsItemClicked {
//
//    private lateinit var binding: ActivityNewsListBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityNewsListBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.progressBar.visibility = View.VISIBLE
//        fetchApiData()
//    }
//
//
//    override fun onItemClicked(item: Article) {
//        val intent = Intent(this@NewsListActivity, NewDetails::class.java)
//        Log.d("This is article", item.toString())
//        intent.putExtra("URL", item.url)
//        startActivity(intent)
//    }
//
//
//    private fun fetchApiData() {
//
//
//        val call = ApiClient.apiService.getNewsList();
//        call.enqueue(object : Callback<NewsResponse> {
//            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
//                if (response.isSuccessful) {
//                    val newsResponse: NewsResponse? = response.body()
//                    val newsAdapter = newsResponse?.articles?.let {
//                        NewsListAdapter(
//                            this@NewsListActivity,
//                            it,
//                            this@NewsListActivity
//                        )
//                    }
//                    binding.recyclerView.adapter = newsAdapter
//                    binding.recyclerView.layoutManager = LinearLayoutManager(this@NewsListActivity)
//                    binding.progressBar.visibility = View.GONE
//
//                } else {
//                    Log.d("This is response else", response.toString())
//                    binding.progressBar.visibility = View.GONE
//                }
//            }
//
//            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                Log.d("This is response error", t.toString())
//                binding.progressBar.visibility = View.GONE
//            }
//        })
//    }
//}


// NewsListActivity.kt
package com.peerbits.newsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.peerbits.newsapp.adapter.NewsItemClicked
import com.peerbits.newsapp.adapter.NewsListAdapter
import com.peerbits.newsapp.databinding.ActivityNewsListBinding
import com.peerbits.newsapp.models.Article
import com.peerbits.newsapp.viewmodel.NewsViewModel

class NewsListActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var binding: ActivityNewsListBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        setupRecyclerView()

        // Observe isLoading to show/hide progress bar
        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    private fun setupRecyclerView() {
        val newsAdapter = NewsListAdapter(this, emptyList(), this)
        binding.recyclerView.adapter = newsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe newsList to update RecyclerView data
        viewModel.newsList.observe(this, Observer { articles ->
            newsAdapter.updateData(articles)
        })
    }

    override fun onItemClicked(item: Article) {
        val intent = Intent(this@NewsListActivity, NewDetails::class.java)
        intent.putExtra("URL", item.url)
        startActivity(intent)
    }
}
