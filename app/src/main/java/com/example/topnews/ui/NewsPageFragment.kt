package com.example.topnews.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.topnews.R
import com.example.topnews.models.NewsItem
import com.squareup.picasso.Picasso

class NewsPageFragment : Fragment() {

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var moreTextView: TextView
    private lateinit var newsImageView: ImageView
    
    companion object {
        private const val ARG_NEWS_ITEM = "news_item"

        fun newInstance(newsItem: NewsItem): NewsPageFragment {
            val fragment = NewsPageFragment()
            val args = Bundle()
            args.putParcelable(ARG_NEWS_ITEM, newsItem)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_page, container, false)



        titleTextView = view.findViewById(R.id.titleTextView)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        moreTextView = view.findViewById(R.id.moreTextView)
        newsImageView = view.findViewById(R.id.newsImageView)

        val newsItem = arguments?.getParcelable<NewsItem>(ARG_NEWS_ITEM)
        if (newsItem != null) {
            titleTextView.text = newsItem.title
            descriptionTextView.text = newsItem.description
            Picasso.get().load(newsItem.urlToImage).into(newsImageView)
        }


        // Öffnen des Artikels, wenn auf die 'More...' geklickt wird
        moreTextView.setOnClickListener {

            val articleUrl = newsItem?.url

            if (!articleUrl.isNullOrBlank()) {
                openArticleInBrowser(articleUrl)
            }
        }

        return view
    }


    private fun openArticleInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}
