package com.masuwes.moviecatalogue.ui.movie

import junit.framework.TestCase
import org.junit.Before

class MovieViewModelTest : TestCase() {

    /**
     * melakukan pengecekan viewModel apakah
     * data sesuai dengan jumlah yang diperkirakan (10)
     * **/

    private lateinit var viewModel: MovieViewModel

    @Before
    override fun setUp() {
        viewModel = MovieViewModel()
    }

    fun testGetMovies() {
        val movieEntities = viewModel.getMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }
}