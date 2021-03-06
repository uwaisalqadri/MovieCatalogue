package com.masuwes.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.ui.TvShowListItem
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.FragmentTvShowBinding
import com.masuwes.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import com.masuwes.moviecatalogue.utils.ui.LoadMoreItemView
import com.masuwes.moviecatalogue.utils.ui.PaginationScrollListener
import com.masuwes.moviecatalogue.utils.ui.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.android.ext.android.inject
import timber.log.Timber

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {

    private val binding: FragmentTvShowBinding by viewBinding()

    private val viewModel: TvShowViewModel by inject()
    private val adapterShow = GroupAdapter<GroupieViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var isLastPages = false
    private var loadMoreItemView = LoadMoreItemView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.postTvShowData.observe(viewLifecycleOwner, tvShowObserver)
        viewModel.getTvShows(page)

        populateShows()
    }

    private fun populateShows() {
        val staggeredLayout = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
        binding.rvShow.apply {
            layoutManager = staggeredLayout
            adapter = adapterShow
        }

        binding.rvShow.addOnScrollListener(object : PaginationScrollListener(staggeredLayout) {
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
            messageData.observe(viewLifecycleOwner, { messageInfo ->
                context?.showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressBar.observe(viewLifecycleOwner, {
                binding.progressCircular.isVisible = it
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
                            startActivity(
                                Intent(context, DetailTvShowActivity::class.java)
                                    .putExtra(DetailTvShowActivity.SHOW_ID, item.id)
                            )
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvShow.adapter = null
    }

}





















