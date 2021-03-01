package com.masuwes.favorite.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.ui.FavMovieAdapter
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.Status
import com.masuwes.favorite.databinding.FragmentFavMovieBinding
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.utils.ui.showToast
import org.koin.android.ext.android.inject

class FavMovieFragment : Fragment() {

    private val viewModel: FavMovieViewModel by inject()
    private lateinit var binding: FragmentFavMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavMovieBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            rvFavMovie.setEmptyView(imageView, tvEmpty)
        }
    }

}




















