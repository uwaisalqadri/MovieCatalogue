package com.masuwes.moviecatalogue.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.ui.favorite.movie.FavMovieFragment
import com.masuwes.moviecatalogue.ui.favorite.tvshow.FavTvShowFragment

class FavoritePagerAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitle = intArrayOf(
            R.string.movie,
            R.string.tvShow
    )

    private val pages = listOf(
            FavMovieFragment(),
            FavTvShowFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabTitle[position])
    }


}




















