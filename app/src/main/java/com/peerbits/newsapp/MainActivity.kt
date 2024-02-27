package com.peerbits.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.peerbits.newsapp.databinding.ActivityMainBinding
import com.peerbits.newsapp.models.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()

    }

    private fun initializeUI() {

        binding.loginBtn.setOnClickListener() {
            var username = binding.edUserName.text
            var password = binding.edPassword.text

            val intent = Intent(this@MainActivity, NewsListActivity::class.java)
            startActivity(intent)

//            if (username.isNotEmpty() && password.isNotEmpty()) {
//                val progressDialog = ProgressDialog(this@MainActivity)
//                progressDialog.setMessage("Loading...")
//                progressDialog.show()
//
//                val loginRequest = LoginRequest(username.toString(), password.toString())
//                val call = ApiClient.apiService.loginUser(loginRequest)
//
//                call.enqueue(object : Callback<LoginResponse> {
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>
//                    ) {
//                        val context: Context = this@MainActivity
//                        println("This is response ${response}")
//
//                        if (response.body()?.status == "SUCCESS") {
//                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
//                            progressDialog.hide()
//                            val intent = Intent(this@MainActivity, NewsListActivity::class.java)
//                            startActivity(intent)
//
//                        } else {
//                            Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT)
//                                .show()
//                            progressDialog.hide()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        // Handle failure
//                        progressDialog.hide()
//                        println("Network call failed: ${t.message}")
//                    }
//                })
//
//
//            } else {
//                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT)
//                    .show()
//            }
        }
    }



}

