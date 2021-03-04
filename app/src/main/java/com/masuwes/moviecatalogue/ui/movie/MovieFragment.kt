package com.masuwes.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.utils.Constants
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.ui.MovieListItem
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.FragmentMovieBinding
import com.masuwes.moviecatalogue.databinding.FragmentTvShowBinding
import com.masuwes.moviecatalogue.ui.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.utils.ui.LoadMoreItemView
import com.masuwes.moviecatalogue.utils.ui.PaginationScrollListener
import com.masuwes.moviecatalogue.utils.ui.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.android.ext.android.inject
import timber.log.Timber

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by inject()
    private lateinit var adapterMovie: GroupAdapter<GroupieViewHolder>
    private var page = 1
    private var isLoadMore = false
    private var isLastPages = false
    private var loadMoreItemView = LoadMoreItemView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterMovie = GroupAdapter<GroupieViewHolder>()

        viewModel.postMovieData.observe(viewLifecycleOwner, movieObserver)
        viewModel.getMovies(page)
        populateMovies()
    }

    private fun populateMovies() {
        val staggeredLayout = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        binding.rvMovie.apply {
            layoutManager = staggeredLayout
            adapter = adapterMovie
        }

        binding.rvMovie.addOnScrollListener(object : PaginationScrollListener(staggeredLayout) {
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
            messageData.observe(viewLifecycleOwner, { messageInfo ->
                context?.showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressBar.observe(viewLifecycleOwner, {
                binding.progressCircular.isVisible = it
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}















