package com.masuwes.moviecatalogue.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.databinding.FragmentTvShowBinding
import com.masuwes.moviecatalogue.presentation.detail.tvshow.DetailTvShowActivity
import com.masuwes.moviecatalogue.utils.base.BaseFragment
import com.masuwes.moviecatalogue.utils.ui.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.android.ext.android.inject

class TvShowFragment: BaseFragment<FragmentTvShowBinding>() {

    private val viewModel: TvShowViewModel by inject()
    private val tvShowAdapter = GroupAdapter<GroupieViewHolder>()
    private var page = 1
    private var isLoadMore = false
    private var isLastPages = false

    private var onShowDetail: ((TvShow) -> Unit)? = null

    override fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater)

    override fun initUI() {
        val staggeredLayout = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)

        binding.apply {
            rvShow.apply {
                layoutManager = staggeredLayout
                adapter = tvShowAdapter
            }

            rvShow.addOnScrollListener(object : PaginationScrollListener(staggeredLayout) {
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
        }
    }

    override fun initAction() {
        onShowDetail = {
            DetailTvShowActivity.start(requireContext(), it.id)
        }
    }

    override fun initProcess() {
        viewModel.getTvShows(page)
    }

    override fun initObservers() {
        viewModel.tvShowsResult.observeData(
            owner = viewLifecycleOwner,
            context = requireContext(),
            onLoading = {
                showLoading()
            },
            onSuccess = {
                hideLoading()
                setTvShows(it)
            },
            onFailure = {
                hideLoading()
            }
        )
    }

    private fun setTvShows(tvShows: List<TvShow>) {
        tvShowAdapter.clear()
        tvShows.map {
            tvShowAdapter.add(TvShowListItem(it, onShowDetail))
        }
    }

    override fun showLoading() {
        binding.progressCircular.isVisible = true
    }

    override fun hideLoading() {
        binding.progressCircular.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvShow.adapter = null
    }

}