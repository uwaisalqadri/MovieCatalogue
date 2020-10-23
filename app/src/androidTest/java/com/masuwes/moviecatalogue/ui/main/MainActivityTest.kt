package com.masuwes.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.masuwes.moviecatalogue.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest  {

    /**
     * -- loadMovie --
     * melakukan pengecekan pada rv_movie dengan menscroll ke position ke 7
     * lalu mengklik item pada position ke 6 setelah itu mengklik back ketika telah masuk ke detailActivity
     * mengecek apakah rv_movie telah tampil
     * mengecek apakah jumlah item pada rv_movie sesuai dengan ekpektasi (10)
     *
     * -- loadTv --
     * melakukan swipe pada view_pager ke arah kiri
     * melakukan pengecekan pada rv_show dengan menscroll ke position ke 7
     * lalu mengklik item pada position ke 6 setelah itu mengklik back ketika telah masuk ke detailActivity
     * mengecek apakah rv_show telah tampil
     * mengecek apakah jumlah item pada rv_show sesuai dengan ekpektasi (10)
     * **/

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Thread.sleep(3000)

        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                6, ViewActions.click()
        ))
        Thread.sleep(3000)
        Espresso.pressBack()
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(10))
    }

    @Test
    fun loadTv() {
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Thread.sleep(3000)
        onView(withId(R.id.rv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(6, ViewActions.click()))
        Thread.sleep(3000)
        Espresso.pressBack()
        onView(withId(R.id.rv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_show)).check(RecyclerViewItemCountAssertion(10))
    }

}
















