package com.peerbits.newsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.peerbits.newsapp.databinding.ActivityMainBinding
import com.peerbits.newsapp.models.LoginViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initializeUI()

    }

    private fun initializeUI() {
        viewModel.loading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.loginResult.observe(this) { loginSuccessful ->
            if (loginSuccessful) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, NewsListActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.emptyCred.observe(this) { isEmptyCred ->
            if (isEmptyCred) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            } else {
                // Only show "Invalid Credentials" if login was not successful
                viewModel.loginResult.value?.let { loginSuccessful ->
                    if (!loginSuccessful) {
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }



}