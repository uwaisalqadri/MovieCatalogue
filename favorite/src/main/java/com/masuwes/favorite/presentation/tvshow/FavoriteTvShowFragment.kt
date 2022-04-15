package com.masuwes.favorite.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.utils.Constants
import com.masuwes.favorite.databinding.FragmentFavTvshowBinding
import com.masuwes.moviecatalogue.presentation.detail.tvshow.DetailTvShowActivity
import com.masuwes.moviecatalogue.utils.base.BaseFragment
import com.masuwes.moviecatalogue.utils.ui.observeData
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.android.ext.android.inject

class FavoriteTvShowFragment: BaseFragment<FragmentFavTvshowBinding>() {

    private val viewModel: FavoriteTvShowViewModel by inject()
    private val tvShowAdapter = GroupAdapter<GroupieViewHolder>()

    private var onItemSelected: ((DetailTvShow) -> Unit)? = null

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentFavTvshowBinding = FragmentFavTvshowBinding.inflate(layoutInflater)

    override fun initUI() {
        binding.apply {
            rvFavTvshow.apply {
                layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
                adapter = tvShowAdapter
            }
        }
    }

    override fun initAction() {
        onItemSelected = {
            DetailTvShowActivity.start(requireContext(), it.id)
        }
    }

    override fun initProcess() {
        viewModel.getFavoriteTvShow()
    }

    override fun initObservers() {
        viewModel.favoriteTvShows.observeData(
            owner = viewLifecycleOwner,
            context = requireContext(),
            onLoading = {
                showLoading()
            },
            onSuccess = {
                hideLoading()
                setFavorite(it)
            },
            onFailure = {
                hideLoading()
            }
        )
    }

    private fun setFavorite(tvShows: List<DetailTvShow>) {
        tvShowAdapter.clear()
        tvShows.map {
            tvShowAdapter.add(FavoriteTvShowListItem(it, onItemSelected))
        }
    }

    override fun showLoading() {
       binding.progressCircularFavtvshow.isVisible = true
    }

    override fun hideLoading() {
        binding.progressCircularFavtvshow.isVisible = false
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTvShow()
    }

}


















