package com.masuwes.favorite.presentation

import com.masuwes.favorite.R
import com.masuwes.favorite.databinding.ActivityFavoriteBinding
import com.masuwes.favorite.di.favoriteViewModelModule
import com.masuwes.moviecatalogue.utils.base.BaseActivity
import org.koin.core.context.loadKoinModules

class FavoriteActivity: BaseActivity<ActivityFavoriteBinding>() {

    override fun getViewBinding(): ActivityFavoriteBinding =
        ActivityFavoriteBinding.inflate(layoutInflater)

    override fun initUI() {
        supportActionBar?.apply {
            title = getString(R.string.favorite)
            setDisplayHomeAsUpEnabled(true)
        }

        binding.apply {
            viewPagerFavorite.adapter = FavoritePagerAdapter(this@FavoriteActivity, supportFragmentManager)
            tabLayout.setupWithViewPager(viewPagerFavorite)
        }
    }

    override fun initProcess() {
        loadKoinModules(favoriteViewModelModule)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}