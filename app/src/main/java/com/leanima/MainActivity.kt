package com.leanima

import ItemAdapter
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.annotation.OptIn
import com.leanima.docker.php.www.ApiService
import com.leanima.docker.php.www.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leanima.database.entity.Item

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    @OptIn(UnstableApi::class) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitClient.instance

        if (isNetworkAvailable()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = apiService.getItems("getItems").execute()
                    if (response.isSuccessful) {
                        val items = response.body() ?: emptyList()
                        withContext(Dispatchers.Main) {
                            updateUI(items)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Log.e("MainActivity", "Response Error: ${response.code()}")
                        }
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "API call failed", e)
                }
            }
        } else {
            Log.e("MainActivity", "Network is not available")
        }
    }

    private fun updateUI(items: List<Item>) {
        val itemContainer: RecyclerView = findViewById(R.id.itemContainer)
        itemContainer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = ItemAdapter(items)
        itemContainer.adapter = adapter
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}
