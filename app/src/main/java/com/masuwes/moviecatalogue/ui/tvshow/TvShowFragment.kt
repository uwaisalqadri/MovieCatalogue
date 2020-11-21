package com.masuwes.moviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.utils.ui.isRefresh
import com.masuwes.moviecatalogue.utils.ui.showToast
import kotlinx.android.synthetic.main.fragment_tv_show.*
import kotlinx.android.synthetic.main.fragment_tv_show.progress_circular
import org.koin.android.ext.android.inject
import timber.log.Timber

class TvShowFragment : Fragment() {

    private val viewModel: TvShowViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getTvShows()

        populateShows()
    }

    private fun populateShows() {
        val tvShowAdapter = TvShowAdapter()

        with(viewModel) {
            postTvShowData.observe(viewLifecycleOwner, Observer {
                tvShowAdapter.setShows(it)
            })

            messageData.observe(viewLifecycleOwner, Observer { messageInfo ->
                context?.showToast(messageInfo.toString())
                Timber.i(messageInfo.toString())
            })

            showProgressBar.observe(viewLifecycleOwner, Observer {
                progress_circular.isRefresh(it)
            })
        }

        with(rv_show) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

}





















