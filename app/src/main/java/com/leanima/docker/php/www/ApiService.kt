package com.leanima.docker.php.www

import com.leanima.database.entity.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api.php")
    fun getItems(@Query("action") action: String): Call<List<Item>>
}