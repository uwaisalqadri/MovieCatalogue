package com.masuwes.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest  {

    @Rule
    @JvmField var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun testMovie() {
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Thread.sleep(3000)

        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            6, ViewActions.click()
        ))
        onView(withId(R.id.overview_detail)).check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(3000)
        Espresso.pressBack()
    }

    @Test
    fun testShow() {
        onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        onView(withId(R.id.rv_show)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Thread.sleep(3000)

        onView(withId(R.id.rv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            6, ViewActions.click()
        ))
        onView(withId(R.id.overview_detail)).check(ViewAssertions.matches(isDisplayed()))
        Thread.sleep(3000)
        Espresso.pressBack()
    }

}
















