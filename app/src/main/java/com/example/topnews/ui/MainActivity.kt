package com.example.topnews.ui

import NewsViewModel
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.topnews.adapters.MyPagerAdapter
import com.example.topnews.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var categorySpinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        dotsIndicator = findViewById(R.id.dotsIndicator)

        // Initialisieren des ViewModels
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        // Beobachten von Änderungen in den Nachrichten und Aktualisieren des UI
        newsViewModel.newsItems.observe(this) { newsList ->
            val pagerAdapter = MyPagerAdapter(this, newsList)
            viewPager.adapter = pagerAdapter
            dotsIndicator.setViewPager2(viewPager)
        }

        categorySpinner = findViewById(R.id.categorySpinner)

        // Erstellen eines ArrayAdapter mit der benutzerdefinierten Layout-Ressource
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            R.layout.spinner_item
        )

        // Setzen des Adapters für den Spinner
        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = resources.getStringArray(R.array.categories_array)[position]

                when(selectedCategory) {
                    "TopNews" -> newsViewModel.loadTopNews()
                    "Austria" -> newsViewModel.loadTopNewsAustria()
                    "Sport" -> newsViewModel.loadTopNewsSports()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Optional: Aktionen, wenn nichts ausgewählt ist
            }
        }

    }

}