package com.masuwes.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)

        binding.apply {
            viewPagerFavorite.adapter = FavoritePagerAdapter(view.context, childFragmentManager)
            tabLayout.setupWithViewPager(viewPagerFavorite)
        }
    }

}