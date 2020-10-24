package com.masuwes.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.masuwes.moviecatalogue.R
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
        val movieAdapter = MovieAdapter()

        with(viewModel) {
            postMovieData.observe(viewLifecycleOwner, Observer {
                movieAdapter.setMoviesData(it)
            })

            messageData.observe(viewLifecycleOwner, Observer { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })

            showProgressBar.observe(viewLifecycleOwner, Observer {

            })
        }

        with(rv_movie) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}














