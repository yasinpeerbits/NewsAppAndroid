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
