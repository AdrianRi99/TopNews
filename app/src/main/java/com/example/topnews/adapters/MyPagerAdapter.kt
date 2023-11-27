package com.example.topnews.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.topnews.models.NewsItem
import com.example.topnews.ui.NewsPageFragment

class MyPagerAdapter(fragmentActivity: FragmentActivity, private val newsList: List<NewsItem>) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        val newsItem = newsList[position]
        return NewsPageFragment.newInstance(newsItem)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
