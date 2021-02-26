package com.masuwes.search.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.domain.model.Search
import com.masuwes.core.ui.SearchResultListItem
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.LoadMoreItemView
import com.masuwes.moviecatalogue.utils.ui.showToast
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

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    private val searchViewModel by inject<SearchViewModel>()
    private val adapterSearch = GroupAdapter<GroupieViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var isLastPages = false
    private var loadMoreItemView = LoadMoreItemView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        loadKoinModules(searchViewModelModule)

        var job: Job? = null
        binding.edtSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        searchViewModel.searchAll(editable.toString(), 1)
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
                // TODO = "give some progress indicator"
            })
        }

        with(binding.rvSearchResult) {
            layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
            adapter = adapterSearch
        }
    }

    private val searchObserver = Observer<SearchState> { searchState ->
        when(searchState) {
            is LoadingState -> {
                if (isLoadMore) {
                    adapterSearch.add(loadMoreItemView)
                }
            }

            is SearchDataLoaded -> {
                if (isLoadMore) {
                    adapterSearch.remove(loadMoreItemView)
                    isLoadMore = false
                }

                if (page == 1) adapterSearch.clear()

                searchState.search.map {
                    adapterSearch.add(SearchResultListItem(it, object : SearchResultListItem.OnItemClick {
                        override fun onClick(item: Search) {
                            showToast(item.media_type + item.media_type)
                        }

                    }))
                }
            }

            is DataNotFoundState -> {
                adapterSearch.clear()
            }

            is LastPageState -> {
                if (isLoadMore) {
                    adapterSearch.remove(loadMoreItemView)
                    isLoadMore = false
                    if (!isLastPages) {
                        showToast("Last Page")
                        isLastPages = true
                    }
                }
            }
        }
    }
}

















