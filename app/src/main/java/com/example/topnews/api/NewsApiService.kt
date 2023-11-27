package com.example.topnews.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse> // 'NewsResponse' ist eine Klasse, die die Antwort der API repräsentiert

    @GET("top-headlines")
    suspend fun getTopNewsAustria(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

    @GET("top-headlines")
    suspend fun getTopNewsSports(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}
