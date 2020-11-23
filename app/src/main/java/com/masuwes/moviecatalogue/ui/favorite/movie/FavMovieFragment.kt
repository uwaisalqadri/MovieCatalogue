package com.masuwes.moviecatalogue.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.room.Status
import com.masuwes.moviecatalogue.utils.ui.isRefresh
import com.masuwes.moviecatalogue.utils.ui.showToast
import kotlinx.android.synthetic.main.fragment_fav_movie.*
import org.koin.android.ext.android.inject


class FavMovieFragment : Fragment() {

    private val viewModel: FavMovieViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progress_circular_favmovie.visibility = View.VISIBLE

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

        viewModel.getMoviePage.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                when(response.status) {
                    Status.LOADING -> {
                        progress_circular_favmovie.isRefresh(true)
                    }

                    Status.SUCCESS -> {
                        progress_circular_favmovie.isRefresh(false)
                        movieAdapter?.submitList(response.data)
                        movieAdapter?.notifyDataSetChanged()
                    }

                    Status.ERROR -> {
                        progress_circular_favmovie.isRefresh(true)
                        context?.showToast(getString(R.string.error))
                    }
                }
            }
        })

        rv_fav_movie.apply {
            layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
            adapter = movieAdapter
            setEmptyView(imageView, tvEmpty)
        }
    }


    override fun onResume() {
        super.onResume()
        rv_fav_movie.setEmptyView(imageView, tvEmpty)
    }

}




















