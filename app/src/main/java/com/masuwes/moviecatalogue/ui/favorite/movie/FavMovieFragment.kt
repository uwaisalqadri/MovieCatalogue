package com.masuwes.moviecatalogue.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.utils.Constants
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.ui.FavMovieAdapter
import com.masuwes.core.utils.Status
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.FragmentFavMovieBinding
import com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.utils.ui.showToast
import org.koin.android.ext.android.inject


class FavMovieFragment : Fragment(R.layout.fragment_fav_movie) {

    private val viewModel: FavMovieViewModel by inject()
    private lateinit var binding: FragmentFavMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavMovieBinding.bind(view)

        binding.apply {
            progressCircularFavmovie.visibility = View.VISIBLE

            val movieAdapter = context?.let {
                FavMovieAdapter(it, object : FavMovieAdapter.OnItemClick {
                    override fun onClick(item: DetailMovie) {
                        startActivity(
                            Intent(context, DetailMovieActivity::class.java)
                                .putExtra(DetailMovieActivity.MOVIE_ID, item.id)
                        )
                    }

                })
            }

            viewModel.getMoviePage.observe(viewLifecycleOwner, { response ->
                if (response != null) {
                    when(response.status) {
                        Status.LOADING -> {
                            progressCircularFavmovie.isVisible = true
                        }

                        Status.SUCCESS -> {
                            progressCircularFavmovie.isVisible = false
                            movieAdapter?.submitList(response.data)
                            movieAdapter?.notifyDataSetChanged()
                        }

                        Status.ERROR -> {
                            progressCircularFavmovie.isVisible = false
                            context?.showToast(getString(R.string.error))
                        }
                    }
                }
            })

            rvFavMovie.apply {
                layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
                adapter = movieAdapter
                setEmptyView(imageView, tvEmpty)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        binding.apply {
            rvFavMovie.setEmptyView(imageView, tvEmpty)
        }
    }

}




















