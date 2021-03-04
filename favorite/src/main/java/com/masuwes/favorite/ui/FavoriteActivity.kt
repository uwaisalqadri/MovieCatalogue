package com.masuwes.favorite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.masuwes.favorite.R
import com.masuwes.favorite.databinding.ActivityFavoriteBinding
import com.masuwes.favorite.di.favoriteViewModelModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity(R.layout.activity_favorite) {

    private val binding: ActivityFavoriteBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteViewModelModule) // koin
        supportActionBar?.apply {
            title = getString(R.string.favorite)
            setDisplayHomeAsUpEnabled(true)
        }

        binding.apply {
            viewPagerFavorite.adapter = FavoritePagerAdapter(this@FavoriteActivity, supportFragmentManager)
            tabLayout.setupWithViewPager(viewPagerFavorite)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}