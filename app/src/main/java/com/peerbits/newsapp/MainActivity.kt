//package com.peerbits.newsapp
//
//import android.app.ProgressDialog
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.peerbits.newsapp.databinding.ActivityMainBinding
//import com.peerbits.newsapp.models.LoginResponse
//import com.peerbits.newsapp.services.ApiClient
//import com.peerbits.newsapp.services.LoginRequest
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        initializeUI()
//
//    }
//
//    private fun initializeUI() {
//
//        binding.loginBtn.setOnClickListener() {
//            var username = binding.edUserName.text
//            var password = binding.edPassword.text
//
//
//
//            if (username.isNotEmpty() && password.isNotEmpty()) {
//                val progressDialog = ProgressDialog(this@MainActivity)
//                progressDialog.setMessage("Loading...")
//                progressDialog.show()
//
//                val loginRequest = LoginRequest(username.toString(), password.toString())
//                //val call = ApiClient.apiService.loginUser(loginRequest)
//
//                val call = ApiClient.createApiService("http://dev2.prep.study/api/").loginUser(loginRequest)
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
//        }
//    }
//
//
//}
//


package com.peerbits.newsapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.peerbits.newsapp.databinding.ActivityMainBinding
import com.peerbits.newsapp.models.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        initializeUI()

    }

    private fun initializeUI() {

        binding.loginBtn.setOnClickListener() {
            var username = binding.edUserName.text
            var password = binding.edPassword.text

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val progressDialog = ProgressDialog(this@MainActivity)
                progressDialog.setMessage("Loading...")
                progressDialog.show()

                viewModel.loginUser(username.toString(), password.toString(),
                    onLoginSuccess = {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        progressDialog.hide()
                        val intent = Intent(this@MainActivity, NewsListActivity::class.java)
                        startActivity(intent)
                    },
                    onLoginFailure = {
                        Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        progressDialog.hide()
                    }
                )

            } else {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


}

