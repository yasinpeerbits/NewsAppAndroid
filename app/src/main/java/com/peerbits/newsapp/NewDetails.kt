package com.peerbits.newsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.peerbits.newsapp.databinding.ActivityNewDetailsBinding

class NewDetails : AppCompatActivity() {

    private lateinit var binding: ActivityNewDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBarNewsDetails.visibility = View.VISIBLE
        val url: String? = intent.getStringExtra("URL")
        if (url != null) {
            binding.detailsWebView.settings.javaScriptEnabled = true
            binding.detailsWebView.loadUrl("https://www.peerbits.com/")
            binding.progressBarNewsDetails.visibility = View.GONE
        }


    }
}