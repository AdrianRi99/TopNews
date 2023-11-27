package com.example.topnews

import NewsViewModel
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.topnews.ui.MainActivity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class EndToEndTest {

    private var scenario: ActivityScenario<MainActivity>? = null

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(5000)
    }

    @After
    fun tearDown() {
        scenario?.close()
    }

    @Test
    fun checkCorrectNewsItemsSize() {
        var newsItemsSize = 0
        scenario?.onActivity { activity ->
            val viewModel = ViewModelProvider(activity)[NewsViewModel::class.java]
            newsItemsSize = viewModel.newsItems.value!!.size
        }

        Assert.assertEquals(6, newsItemsSize)
    }

    @Test
    fun checkWrongNewsItemsSize() {
        var newsItemsSize = 0
        scenario?.onActivity { activity ->
            val viewModel = ViewModelProvider(activity)[NewsViewModel::class.java]
            newsItemsSize = viewModel.newsItems.value!!.size
        }

        println(newsItemsSize)

        Assert.assertNotEquals(7, newsItemsSize)
    }


    @Test
    fun checkIfUrlContainsSearchedTerm() {

        var url = ""

        onView(withId(R.id.categorySpinner)).perform(ViewActions.click())

        onView(withText("Austria")).perform(ViewActions.click())

        // Warten auf die Aktualisierung der URL
        Thread.sleep(5000)

        scenario?.onActivity { activity ->
            val viewModel = ViewModelProvider(activity)[NewsViewModel::class.java]
            url = viewModel.newsItems.value!![0].url
        }

        Assert.assertTrue(url.contains("news.google"))
    }

    @Test
    fun checkIfTheBrowserOpens() {
        // Initialisieren der Intent-Erfassung
        Intents.init()

        onView(withText("More...")).perform(ViewActions.click())

        // Überprüfen, ob die Absicht zum Öffnen des Browsers ausgelöst wurde
        Intents.intended(IntentMatchers.hasAction(Intent.ACTION_VIEW))

        // Freigeben der Intent-Erfassung
        Intents.release()
    }


    @Test
    fun checkSwipingViewPager() {
        var swipeCount = 0
        var newsItemsSize = 0

        scenario?.onActivity { activity ->
            val viewModel = ViewModelProvider(activity)[NewsViewModel::class.java]
            newsItemsSize = viewModel.newsItems.value!!.size - 1
        }

        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))

        repeat(newsItemsSize) {
            onView(withId(R.id.viewPager))
                .perform(ViewActions.swipeLeft())
            swipeCount++
        }

        Assert.assertEquals(5, swipeCount)
    }


}

