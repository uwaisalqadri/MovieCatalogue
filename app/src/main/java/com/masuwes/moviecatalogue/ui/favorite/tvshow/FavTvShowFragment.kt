package com.masuwes.moviecatalogue.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.utils.Constants
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.ui.FavTvShowAdapter
import com.masuwes.core.utils.Status
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.FragmentFavTvshowBinding
import com.masuwes.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import com.masuwes.moviecatalogue.utils.ui.showToast
import org.koin.android.ext.android.inject

class FavTvShowFragment : Fragment(R.layout.fragment_fav_tvshow) {

    private val viewModel: FavTvShowViewModel by inject()
    private lateinit var binding: FragmentFavTvshowBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavTvshowBinding.bind(view)

        binding.apply {
            progressCircularFavtvshow.visibility = View.VISIBLE

            val tvShowAdapter = context?.let {
                FavTvShowAdapter(it, object : FavTvShowAdapter.OnItemClick {
                    override fun onClick(item: DetailTvShow) {
                        startActivity(
                            Intent(context, DetailTvShowActivity::class.java)
                                .putExtra(DetailTvShowActivity.SHOW_ID, item.id)
                        )
                    }

                })
            }

            viewModel.getTvShowPage.observe(viewLifecycleOwner, { response ->
                if (response != null) {
                    when(response.status) {
                        Status.LOADING -> {
                            progressCircularFavtvshow.isVisible = true
                        }

                        Status.SUCCESS -> {
                            progressCircularFavtvshow.isVisible = false
                            tvShowAdapter?.submitList(response.data)
                            tvShowAdapter?.notifyDataSetChanged()
                        }

                        Status.ERROR -> {
                            progressCircularFavtvshow.isVisible = false
                            context?.showToast(getString(R.string.error))
                        }
                    }
                }
            })

            rvFavTvshow.apply {
                layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
                adapter = tvShowAdapter
                setEmptyView(imageView, tvEmpty)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            rvFavTvshow.setEmptyView(imageView, tvEmpty)
        }
    }

}


















