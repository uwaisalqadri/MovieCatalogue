package com.masuwes.search.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.masuwes.core.domain.model.Search
import com.masuwes.core.ui.SearchHistoryAdapter
import com.masuwes.core.ui.SearchResultListItem
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.*
import com.masuwes.search.R
import com.masuwes.search.databinding.ActivitySearchBinding
import com.masuwes.search.di.searchViewModelModule
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import timber.log.Timber

class SearchActivity : AppCompatActivity(R.layout.activity_search) {

    private val binding: ActivitySearchBinding by viewBinding()

    private val searchViewModel by inject<SearchViewModel>()
    private val adapterSearch = GroupAdapter<GroupieViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var loadMoreItemView = LoadMoreItemView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        loadKoinModules(searchViewModelModule)
        showRecentSearch()

        binding.deleteRecent.setOnClickListener {
            searchViewModel.deleteAllHistories()
            binding.apply {
                recent.isVisible = false
                deleteRecent.isVisible = false
                rvSearchHistory.isVisible = false
            }
        }

        var job: Job? = null
        binding.edtSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(200L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        searchViewModel.searchAll(editable.toString(), page)
                        binding.btnBack.apply {
                            setImageResource(R.drawable.ic_back)
                            setOnClickListener {
                                binding.edtSearch.setText("")
                            }
                        }
                    } else {
                        binding.apply {
                            btnBack.apply {
                                setImageResource(R.drawable.ic_back)
                                setOnClickListener { finish() }
                            }
                        }
                    }
                }
            }
        }

        with(searchViewModel) {
            postsData.observe(this@SearchActivity, searchObserver)

            messageData.observe(this@SearchActivity, { messageInfo ->
                showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressbar.observe(this@SearchActivity, {
                binding.pgSearchResult.isVisible = it
            })
        }

        with(binding.rvSearchResult) {
            layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
            adapter = adapterSearch
        }
    }

    private fun showRecentSearch() {
        val recentAdapter = SearchHistoryAdapter(object : SearchHistoryAdapter.OnItemClick {
            override fun onClick(item: Search) {
                when(item.media_type) {
                    Constants.TYPE_MOVIE -> {
                        openMovieDetailActivity(this@SearchActivity, item.id)
                    }
                    Constants.TYPE_SHOW -> {
                        openTvShowDetailActivity(this@SearchActivity, item.id)
                    }
                }
            }

        })

        searchViewModel.getSearchHistories.observe(this, { searchData ->
            if (searchData.isNotEmpty()) {
                recentAdapter.differ.submitList(searchData)
                binding.apply {
                    recent.isVisible = true
                    deleteRecent.isVisible = true
                    rvSearchHistory.isVisible = true
                }
            } else {
                binding.apply {
                    recent.isVisible = false
                    deleteRecent.isVisible = false
                    rvSearchHistory.isVisible = false
                }
            }
        })

        with(binding.rvSearchHistory) {
            layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recentAdapter
            setHasFixedSize(true)
        }
    }

    private val searchObserver = Observer<SearchState> { searchState ->
        when(searchState) {

            is SearchDataLoaded -> {
                if (isLoadMore) {
                    adapterSearch.remove(loadMoreItemView)
                    isLoadMore = false
                }

                if (page == 1) adapterSearch.clear()

                searchState.search.map {
                    adapterSearch.add(SearchResultListItem(it, object : SearchResultListItem.OnItemClick {
                        override fun onClick(item: Search) {
                            when (item.media_type) {
                                Constants.TYPE_SHOW -> {
                                    openTvShowDetailActivity(this@SearchActivity, item.id)
                                    searchViewModel.insertHistory(item)
                                }
                                Constants.TYPE_MOVIE -> {
                                    openMovieDetailActivity(this@SearchActivity, item.id)
                                    searchViewModel.insertHistory(item)
                                }
                            }
                        }

                    }))

                    binding.apply {
                        emptySearch.isVisible = false
                        tvEmpty.isVisible = false
                    }
                }
            }

            is DataNotFoundState -> {
                adapterSearch.clear()
                binding.apply {
                    pgSearchResult.isVisible = false
                    emptySearch.isVisible = true
                    tvEmpty.isVisible = true
                }
            }
        }
    }
}

















