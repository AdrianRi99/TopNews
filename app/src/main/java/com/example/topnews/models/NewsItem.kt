package com.example.topnews.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NewsItem(
    val title: String,
    val description: String,
    val urlToImage: String,
    val url: String
) : Parcelable




//data class NewsItem(
//    val title: String,
//    val description: String,
//    val urlToImage: String
//)
