package com.masuwes.search.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masuwes.search.databinding.ActivitySearchBinding
import com.masuwes.search.di.searchViewModelModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel by inject<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(searchViewModelModule)

        searchViewModel.searchAll("Fish", 1)
    }
}