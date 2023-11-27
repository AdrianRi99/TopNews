package com.example.topnews.repositories

import com.example.topnews.api.NewsApiService
import com.example.topnews.models.NewsItem

class NewsRepository {

    suspend fun getTopNews(apiService: NewsApiService, apiKey: String): List<NewsItem> {
        val response = apiService.getTopNews("bbc-news", apiKey)
        if (response.isSuccessful) {
            val newsList = response.body()?.articles ?: emptyList()
            // Begrenzen auf 10 News
            return newsList.take(6)
        }
        return emptyList()
    }

    suspend fun getTopNewsAustria(apiService: NewsApiService, apiKey: String): List<NewsItem> {
        val response = apiService.getTopNewsAustria("at", apiKey)
        if (response.isSuccessful) {
            val newsList = response.body()?.articles ?: emptyList()
            // Begrenzen auf 10 News
            return newsList.take(6)
        }
        return emptyList()
    }

    suspend fun getTopNewsSports(apiService: NewsApiService, apiKey: String): List<NewsItem> {
        val response = apiService.getTopNewsSports("gb", "sports", apiKey)
        if (response.isSuccessful) {
            val newsList = response.body()?.articles ?: emptyList()
            // Begrenzen auf 10 News
            return newsList.take(6)
        }
        return emptyList()
    }
}
