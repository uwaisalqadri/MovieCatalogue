package com.masuwes.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.masuwes.moviecatalogue.model.TvShowModel
import com.masuwes.moviecatalogue.utils.Dummy

class TvShowViewModel : ViewModel() {
    fun getTvShows(): List<TvShowModel> = Dummy.generateDummyTvShow()
}