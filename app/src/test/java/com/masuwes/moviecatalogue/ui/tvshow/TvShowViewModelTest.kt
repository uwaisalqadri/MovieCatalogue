package com.masuwes.moviecatalogue.ui.tvshow

import junit.framework.TestCase
import org.junit.Before

class TvShowViewModelTest : TestCase() {

    /**
     * melakukan pengecekan viewModel apakah
     * data sesuai dengan jumlah yang diperkirakan (10)
     * **/

    private lateinit var viewModel: TvShowViewModel

    @Before
    override fun setUp() {
        viewModel = TvShowViewModel()
    }

    fun testGetTvShows() {
        val showEntities = viewModel.getTvShows()
        assertNotNull(showEntities)
        assertEquals(10, showEntities.size)
    }
}