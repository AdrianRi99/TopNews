package com.example.topnews.api

import com.example.topnews.models.NewsItem

data class NewsResponse(
    val status: String,
    val articles: List<NewsItem>
)
