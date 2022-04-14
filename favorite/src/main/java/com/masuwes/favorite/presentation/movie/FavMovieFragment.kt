package com.masuwes.favorite.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.utils.Constants
import com.masuwes.favorite.databinding.FragmentFavMovieBinding
import com.masuwes.moviecatalogue.presentation.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.utils.base.BaseFragment
import com.masuwes.moviecatalogue.utils.ui.observeData
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.android.ext.android.inject

class FavMovieFragment: BaseFragment<FragmentFavMovieBinding>() {

    private val viewModel: FavMovieViewModel by inject()
    private val movieAdapter = GroupAdapter<GroupieViewHolder>()

    private var onItemSelect: ((DetailMovie) -> Unit)? = null

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentFavMovieBinding = FragmentFavMovieBinding.inflate(layoutInflater)

    override fun initUI() {
        binding.apply {
            rvFavMovie.apply {
                layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
                adapter = movieAdapter
                setEmptyView(imageView, tvEmpty)
            }
        }
    }

    override fun initAction() {
        onItemSelect = {
            DetailMovieActivity.start(requireContext(), it.id)
        }
    }

    override fun initProcess() {
        viewModel.getFavoriteMovie()
    }

    override fun initObservers() {
        viewModel.favoriteMovies.observeData(
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

    private fun setFavorite(movies: List<DetailMovie>) {
//        movieAdapter.clear()
        movies.map {
            movieAdapter.add(FavMovieListItem(it, onItemSelect))
        }
    }

    override fun showLoading() {
        binding.progressCircularFavmovie.isVisible = true
    }

    override fun hideLoading() {
        binding.progressCircularFavmovie.isVisible = false
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovie()
        binding.apply {
            rvFavMovie.setEmptyView(imageView, tvEmpty)
        }
    }

}




















