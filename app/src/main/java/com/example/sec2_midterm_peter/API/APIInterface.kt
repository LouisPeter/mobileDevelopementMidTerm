package com.example.sec2_midterm_peter

import com.example.sec2_midterm_peter.API.DataAPIItem
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("albums")
    fun getAPIData(): Call<List<DataAPIItem>>
}