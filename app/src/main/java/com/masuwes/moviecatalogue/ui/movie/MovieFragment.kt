package com.masuwes.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.room.PaginationScrollListener
import com.masuwes.moviecatalogue.utils.ui.LoadMoreItemView
import com.masuwes.moviecatalogue.utils.ui.isRefresh
import com.masuwes.moviecatalogue.utils.ui.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.progress_circular
import org.koin.android.ext.android.inject
import timber.log.Timber

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by inject()
    private val adapterMovie = GroupAdapter<ViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var isLastPages = false
    private var loadMoreItemView = LoadMoreItemView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.postMovieData.observe(viewLifecycleOwner, movieObserver)
        viewModel.getMovies(page)
        populateMovies()
    }

    private fun populateMovies() {
        val staggeredLayout = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        rv_movie.apply {
            layoutManager = staggeredLayout
            adapter = adapterMovie
        }

        rv_movie.addOnScrollListener(object : PaginationScrollListener(staggeredLayout) {
            override fun loadMoreItems() {
                isLoadMore = true
                page++
                viewModel.getMovies(page)
            }

            override val isLastPage: Boolean
                get() = isLastPages
            override val isLoading: Boolean
                get() = isLoadMore

        })

        with(viewModel) {
            messageData.observe(viewLifecycleOwner, Observer { messageInfo ->
                context?.showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressBar.observe(viewLifecycleOwner, Observer {
                progress_circular.isRefresh(it)
            })
        }
    }

    private val movieObserver = Observer<MovieState> { movieState ->
        when(movieState) {
            is LoadingState -> {
                if (isLoadMore) {
                    adapterMovie.add(loadMoreItemView)
                }
            }

            is MovieDataLoaded -> {
                if (isLoadMore) {
                    adapterMovie.remove(loadMoreItemView)
                    isLoadMore = false
                }

                if (page == 1) adapterMovie.clear()

                movieState.movie.map {
                    adapterMovie.add(MovieListItem(it, object : MovieListItem.OnItemClick {
                        override fun onClick(item: Movie) {
                            startActivity(
                                Intent(context, DetailMovieActivity::class.java)
                                .putExtra(DetailMovieActivity.MOVIE_ID, item.id))
                        }

                    }))
                }
            }

            is DataNotFoundState -> {
                adapterMovie.clear()
            }

            is LastPageState -> {
                if (isLoadMore) {
                    adapterMovie.remove(loadMoreItemView)
                    isLoadMore = false
                    if (!isLastPages) {
                        context?.showToast("Last Page")
                        isLastPages = true
                    }
                }
            }
        }
    }


}















