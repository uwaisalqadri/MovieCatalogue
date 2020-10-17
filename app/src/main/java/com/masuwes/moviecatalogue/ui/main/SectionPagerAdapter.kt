package com.masuwes.moviecatalogue.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.ui.movie.MovieFragment
import com.masuwes.moviecatalogue.ui.tvshow.TvShowFragment

class SectionPagerAdapter(private val context: Context, fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val tabTitles = intArrayOf(
                R.string.movie,
                R.string.tvShow
        )
    }

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(tabTitles[position])

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> MovieFragment()
        1 -> TvShowFragment()
        else -> Fragment()
    }

    override fun getCount(): Int = 2

}













