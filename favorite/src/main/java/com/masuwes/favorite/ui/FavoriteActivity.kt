package com.masuwes.favorite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masuwes.favorite.R
import com.masuwes.favorite.databinding.ActivityFavoriteBinding
import com.masuwes.favorite.di.favoriteViewModelModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.favorite)
        loadKoinModules(favoriteViewModelModule)

        binding.apply {
            viewPagerFavorite.adapter = FavoritePagerAdapter(this@FavoriteActivity, supportFragmentManager)
            tabLayout.setupWithViewPager(viewPagerFavorite)
        }
    }
}