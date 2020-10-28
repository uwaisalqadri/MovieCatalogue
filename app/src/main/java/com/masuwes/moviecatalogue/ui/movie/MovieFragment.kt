package com.masuwes.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.utils.showToast
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.android.ext.android.inject
import timber.log.Timber

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
        val movieAdapter = MovieAdapter()

        with(viewModel) {
            postMovieData.observe(viewLifecycleOwner, Observer {
                movieAdapter.setMoviesData(it)
            })

            messageData.observe(viewLifecycleOwner, Observer { message ->
                context?.showToast(message.toString())
                Timber.i(message.toString())
            })

            showProgressBar.observe(viewLifecycleOwner, Observer {
                if (it == true) progress_circular.visibility = View.VISIBLE
                else progress_circular.visibility = View.GONE
            })
        }

        with(rv_movie) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}














