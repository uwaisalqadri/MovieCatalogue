package com.masuwes.moviecatalogue.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.databinding.FragmentMovieBinding
import com.masuwes.moviecatalogue.presentation.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.utils.base.BaseFragment
import com.masuwes.moviecatalogue.utils.ui.LoadMoreItemView
import com.masuwes.moviecatalogue.utils.ui.PaginationScrollListener
import com.masuwes.moviecatalogue.utils.ui.observeData
import com.masuwes.moviecatalogue.utils.ui.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.android.ext.android.inject

class MovieFragment: BaseFragment<FragmentMovieBinding>() {

    private val viewModel: MovieViewModel by inject()
    private val movieAdapter = GroupAdapter<GroupieViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var isLastPages = false
    private var loadMoreItemView = LoadMoreItemView()

    private var onShowDetail: ((Movie) -> Unit)? = null

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater)

    override fun initUI() {
        val staggeredLayout = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        binding.apply {
            rvMovie.apply {
                layoutManager = staggeredLayout
                adapter = movieAdapter
            }

            rvMovie.addOnScrollListener(object : PaginationScrollListener(staggeredLayout) {
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
        }
    }

    override fun initAction() {
        onShowDetail = {
            DetailMovieActivity.start(requireContext(), it.id)
        }
    }

    override fun initProcess() {
        viewModel.getMovies(page)
    }

    override fun initObservers() {
        viewModel.moviesResult.observeData(
            owner = viewLifecycleOwner,
            context = requireContext(),
            onLoading = {
                showLoading()
            },
            onSuccess = {
                hideLoading()
                setMovies(it)
            },
            onFailure = {
                hideLoading()
                requireContext().showToast(it.statusMessage)
            }
        )
    }

    private fun setMovies(movies: List<Movie>) {
        movieAdapter.clear()
        movies.map {
            movieAdapter.add(MovieListItem(it, onShowDetail))
        }
    }

//    private val movieObserver = Observer<MovieState> { movieState ->
//        when(movieState) {
//            is LoadingState -> {
//                if (isLoadMore) {
//                    adapterMovie.add(loadMoreItemView)
//                }
//            }
//
//            is MovieDataLoaded -> {
//                if (isLoadMore) {
//                    adapterMovie.remove(loadMoreItemView)
//                    isLoadMore = false
//                }
//
//                if (page == 1) adapterMovie.clear()
//
//                movieState.movie.map {
//                    adapterMovie.add(MovieListItem(it, object : MovieListItem.OnItemClick {
//                        override fun onClick(item: Movie) {
//                            startActivity(
//                                Intent(context, DetailMovieActivity::class.java)
//                                .putExtra(DetailMovieActivity.MOVIE_ID, item.id))
//                        }
//
//                    }))
//                }
//            }
//
//            is DataNotFoundState -> {
//                adapterMovie.clear()
//            }
//
//            is LastPageState -> {
//                if (isLoadMore) {
//                    adapterMovie.remove(loadMoreItemView)
//                    isLoadMore = false
//                    if (!isLastPages) {
//                        context?.showToast("Last Page")
//                        isLastPages = true
//                    }
//                }
//            }
//        }
//    }

    override fun showLoading() {
        binding.progressCircular.isVisible = true
    }

    override fun hideLoading() {
        binding.progressCircular.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvMovie.adapter = null
    }
}















