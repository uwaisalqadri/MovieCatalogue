package com.masuwes.moviecatalogue.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.DetailTvShow
import com.masuwes.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.room.Status
import com.masuwes.moviecatalogue.utils.ui.isRefresh
import com.masuwes.moviecatalogue.utils.ui.showToast
import kotlinx.android.synthetic.main.fragment_fav_tvshow.*
import org.koin.android.ext.android.inject


class FavTvShowFragment : Fragment() {

    private val viewModel: FavTvShowViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progress_circular_favtvshow.visibility = View.VISIBLE

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

        viewModel.getTvShowPage.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                when(response.status) {
                    Status.LOADING -> {
                        progress_circular_favtvshow.isRefresh(true)
                    }

                    Status.SUCCESS -> {
                        progress_circular_favtvshow.isRefresh(false)
                        tvShowAdapter?.submitList(response.data)
                        tvShowAdapter?.notifyDataSetChanged()
                    }

                    Status.ERROR -> {
                        progress_circular_favtvshow.isRefresh(false)
                        context?.showToast(getString(R.string.error))
                    }
                }
            }
        })

        rv_fav_tvshow.apply {
            layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
            adapter = tvShowAdapter
            setEmptyView(imageView, tvEmpty)
        }
    }

    override fun onResume() {
        super.onResume()
        rv_fav_tvshow.setEmptyView(imageView, tvEmpty)
    }

}


















