package com.masuwes.moviecatalogue.utils.ui

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import timber.log.Timber

abstract class PaginationScrollListener constructor() :
    RecyclerView.OnScrollListener() {

    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    constructor(layoutManager: GridLayoutManager) : this() {
        this.mLayoutManager = layoutManager
    }

    constructor(layoutManager: StaggeredGridLayoutManager) : this() {
        this.mLayoutManager = layoutManager
    }

    constructor(layoutManager: LinearLayoutManager) : this() {
        this.mLayoutManager = layoutManager
    }


    /*
     Method gets callback when user scroll the search list
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = mLayoutManager.childCount
        val totalItemCount = mLayoutManager.itemCount
        var firstVisibleItemPosition = 0

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val firstVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(null)
                // get maximum element within the list
                firstVisibleItemPosition = firstVisibleItemPositions[0]
            }
            is GridLayoutManager -> {
                firstVisibleItemPosition =
                    (mLayoutManager as GridLayoutManager).findFirstVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                firstVisibleItemPosition =
                    (mLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            }
        }
        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
            ) {
                Timber.i("Loading more items")
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract val isLastPage: Boolean
    abstract val isLoading: Boolean

    companion object {
        private val TAG = PaginationScrollListener::class.java.simpleName
    }
}