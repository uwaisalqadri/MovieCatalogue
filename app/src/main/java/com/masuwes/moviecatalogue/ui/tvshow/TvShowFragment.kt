package com.masuwes.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.room.PaginationScrollListener
import com.masuwes.moviecatalogue.utils.ui.LoadMoreItemView
import com.masuwes.moviecatalogue.utils.ui.isRefresh
import com.masuwes.moviecatalogue.utils.ui.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_movie.progress_circular
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class TvShowFragment : Fragment() {

    private val viewModel: TvShowViewModel by inject()
    private val adapterShow = GroupAdapter<ViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var isLastPages = false
    private var loadMoreItemView = LoadMoreItemView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.postTvShowData.observe(viewLifecycleOwner, tvShowObserver)
        viewModel.getTvShows(page)

        populateShows()
    }

    private fun populateShows() {
        val staggeredLayout = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        rv_show.apply {
            layoutManager = staggeredLayout
            adapter = adapterShow
        }

        rv_show.addOnScrollListener(object : PaginationScrollListener(staggeredLayout) {
            override fun loadMoreItems() {
                isLoadMore = true
                page++
                viewModel.getTvShows(page)
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

    private val tvShowObserver = Observer<TvShowState> { tvShowState ->
        when(tvShowState) {
            is LoadingState -> {
                if (isLoadMore) {
                    adapterShow.add(loadMoreItemView)
                }
            }

            is TvShowLoadedState -> {
                if (isLoadMore) {
                    adapterShow.remove(loadMoreItemView)
                    isLoadMore = false
                }

                if (page == 1) adapterShow.clear()

                tvShowState.tvShow.map {
                    adapterShow.add(TvShowListItem(it, object : TvShowListItem.OnItemClick {
                        override fun onClick(item: TvShow) {
//                            startActivity(
//                                Intent(context, DetailActivity::class.java)
//                                    .putExtra(DetailActivity.SHOW_ID, item.id.toString()))
                        }
                    }))
                }
            }

            is DataNotFoundState -> {
                adapterShow.clear()
            }

            is LastPageState -> {
                if (isLoadMore) {
                    adapterShow.remove(loadMoreItemView)
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





















