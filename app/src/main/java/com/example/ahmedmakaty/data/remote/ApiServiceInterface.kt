package com.example.ahmedmakaty.data.remote

import io.reactivex.Flowable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("https://newsapi.org/v2/everything")
    fun getLikes(@Query("q") query: String, @Query("apiKey") apiKey: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Flowable<ResponseBody>
}