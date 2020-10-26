package com.masuwes.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.ui.detail.DetailActivity
import com.masuwes.moviecatalogue.ui.detail.DetailActivity.Companion.BUNDLE_KEY
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.ext.android.inject

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()

        populateMovies()
    }

    private fun populateMovies() {
        val movieAdapter = MovieAdapter(object : MovieAdapter.OnItemClick {
            override fun onClick(item: Movie) {
                startActivity(
                    Intent(context, DetailActivity::class.java)
                        .putExtra(BUNDLE_KEY, item.id.toString())
                )
            }

        })

        with(viewModel) {
            postMovieData.observe(viewLifecycleOwner, Observer {
                movieAdapter.setMoviesData(it)
            })

            messageData.observe(viewLifecycleOwner, Observer { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })

            showProgressBar.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    progress_circular.visibility = View.VISIBLE
                } else {
                    progress_circular.visibility = View.GONE
                }
            })
        }

        with(rv_movie) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}














