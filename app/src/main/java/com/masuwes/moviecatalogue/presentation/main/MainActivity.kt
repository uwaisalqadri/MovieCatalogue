package com.masuwes.moviecatalogue.presentation.main

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.onItemSelected
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.ActivityMainBinding
import com.masuwes.moviecatalogue.presentation.movie.MovieFragment
import com.masuwes.moviecatalogue.presentation.tvshow.TvShowFragment
import com.masuwes.moviecatalogue.utils.ui.BaseActivity
import com.masuwes.moviecatalogue.utils.ui.startFavoriteActivity
import com.masuwes.moviecatalogue.utils.ui.startSearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    companion object {
        const val MOVIE = 0
        const val TVSHOW = 1

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initUI() {
        setSupportActionBar(findViewById(R.id.toolbar))

        binding.bottomNav.onItemSelected {
            when(it.itemId) {
                R.id.navigation_movie -> viewModel.setFragmentKey(MOVIE)
                R.id.navigation_tvshow -> viewModel.setFragmentKey(TVSHOW)
            }
        }
    }

    override fun initProcess() {
        if (viewModel.getFragmentKey.value == null) {
            viewModel.setFragmentKey(MOVIE)
        }
    }

    override fun initObservers() {
        viewModel.getFragmentKey.observe(this) {
            val fragment = when (it) {
                MOVIE -> MovieFragment()
                TVSHOW -> TvShowFragment()
                else -> throw IllegalArgumentException("Unknown Fragment Key")
            }
            setFragment(binding.navHostFragment.id, fragment, false)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search -> {
                startSearchActivity(this)
                return true
            }
            R.id.favorite -> {
                startFavoriteActivity(this)
                return true
            }
        }
        return false
    }
}