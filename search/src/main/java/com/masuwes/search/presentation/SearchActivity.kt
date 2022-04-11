package com.masuwes.search.presentation

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.domain.model.Search
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.presentation.detail.movie.DetailMovieActivity
import com.masuwes.moviecatalogue.presentation.detail.tvshow.DetailTvShowActivity
import com.masuwes.moviecatalogue.utils.base.BaseActivity
import com.masuwes.moviecatalogue.utils.ui.LoadMoreItemView
import com.masuwes.moviecatalogue.utils.ui.observeLiveData
import com.masuwes.moviecatalogue.utils.ui.searchListener
import com.masuwes.search.R
import com.masuwes.search.databinding.ActivitySearchBinding
import com.masuwes.search.di.searchViewModelModule
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class SearchActivity: BaseActivity<ActivitySearchBinding>() {

    private val searchViewModel: SearchViewModel by inject()
    private val searchAdapter = GroupAdapter<GroupieViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var loadMoreItemView = LoadMoreItemView()

    private val recentAdapter: SearchHistoryAdapter by lazy {
        SearchHistoryAdapter {
            when(it.mediaType) {
                Constants.TYPE_MOVIE -> {
                    DetailMovieActivity.startFromOutside(this@SearchActivity, it.id)
                }
                Constants.TYPE_SHOW -> {
                    DetailTvShowActivity.startFromOutside(this@SearchActivity, it.id)
                }
            }
        }
    }

    private var onShowDetail: ((Search) -> Unit)? = null

    override fun getViewBinding(): ActivitySearchBinding =
        ActivitySearchBinding.inflate(layoutInflater)

    override fun initUI() {
        supportActionBar?.hide()

        binding.apply {
            rvSearchResult.apply {
                layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
                adapter = searchAdapter
            }

            rvSearchHistory.apply {
                layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = recentAdapter
                setHasFixedSize(true)
            }
        }
    }

    override fun initProcess() {
        loadKoinModules(searchViewModelModule)
    }

    override fun initAction() {
        binding.apply {
            deleteRecent.setOnClickListener {
                searchViewModel.deleteAllHistories()
                recent.isVisible = false
                deleteRecent.isVisible = false
                rvSearchHistory.isVisible = false
            }

            edtSearch.searchListener(
                adapter = searchAdapter,
                onSearch = {
                    searchViewModel.getSearch(it, page)
                    btnBack.apply {
                        setImageResource(R.drawable.ic_back)
                        setOnClickListener {
                            edtSearch.setText("")
                        }
                    }
                },
                onFinish = {
                    btnBack.apply {
                        setImageResource(R.drawable.ic_back)
                        setOnClickListener { finish() }
                    }
                }
            )
        }

        onShowDetail = {
            when (it.mediaType) {
                Constants.TYPE_SHOW -> {
                    DetailTvShowActivity.startFromOutside(this@SearchActivity, it.id)
                    searchViewModel.insertHistory(it)
                }
                Constants.TYPE_MOVIE -> {
                    DetailMovieActivity.startFromOutside(this@SearchActivity, it.id)
                    searchViewModel.insertHistory(it)
                }
            }
        }
    }

    override fun initObservers() {
        searchViewModel.searchResult.observeLiveData(
            owner = this,
            context = this,
            onLoading = {
                showLoading()
            },
            onSuccess = {
                hideLoading()
                setSearch(it)
            }
        )
    }

    private fun setSearch(search: List<Search>) {
        if (search.isNullOrEmpty()) {
            searchAdapter.clear()
            binding.apply {
                pgSearchResult.isVisible = false
                emptySearch.isVisible = true
                tvEmpty.isVisible = true
            }
        } else {
            search.map { searchAdapter.add(SearchResultListItem(it, onShowDetail)) }

            binding.apply {
                emptySearch.isVisible = false
                tvEmpty.isVisible = false
            }
        }
    }

//    private fun showRecentSearch() {
//        searchViewModel.getSearchHistories.observe(this, { searchData ->
//            if (searchData.isNotEmpty()) {
//                recentAdapter.differ.submitList(searchData)
//                binding.apply {
//                    recent.isVisible = true
//                    deleteRecent.isVisible = true
//                    rvSearchHistory.isVisible = true
//                }
//            } else {
//                binding.apply {
//                    recent.isVisible = false
//                    deleteRecent.isVisible = false
//                    rvSearchHistory.isVisible = false
//                }
//            }
//        })
//    }

//    private val searchObserver = Observer<SearchState> { searchState ->
//        when(searchState) {
//
//            is SearchDataLoaded -> {
//                if (isLoadMore) {
//                    searchAdapter.remove(loadMoreItemView)
//                    isLoadMore = false
//                }
//
//                if (page == 1) searchAdapter.clear()
//
//                searchState.search.map {
//                    searchAdapter.add(SearchResultListItem(it, onShowDetail))
//
//                    binding.apply {
//                        emptySearch.isVisible = false
//                        tvEmpty.isVisible = false
//                    }
//                }
//            }
//
//            is DataNotFoundState -> {
//                searchAdapter.clear()
//                binding.apply {
//                    pgSearchResult.isVisible = false
//                    emptySearch.isVisible = true
//                    tvEmpty.isVisible = true
//                }
//            }
//        }
//    }

    override fun showLoading() {
        binding.pgSearchResult.isVisible = true
    }

    override fun hideLoading() {
        binding.pgSearchResult.isVisible = false
    }
}

















