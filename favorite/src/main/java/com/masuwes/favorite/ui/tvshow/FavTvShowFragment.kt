package com.masuwes.favorite.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.ui.FavTvShowAdapter
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.Status
import com.masuwes.favorite.databinding.FragmentFavMovieBinding
import com.masuwes.favorite.databinding.FragmentFavTvshowBinding
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.ui.detail.tvshow.DetailTvShowActivity
import com.masuwes.moviecatalogue.utils.ui.showToast
import org.koin.android.ext.android.inject

class FavTvShowFragment : Fragment() {

    private val viewModel: FavTvShowViewModel by inject()
    private lateinit var binding: FragmentFavTvshowBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavTvshowBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            rvFavTvshow.setEmptyView(imageView, tvEmpty)
        }
    }

}


















