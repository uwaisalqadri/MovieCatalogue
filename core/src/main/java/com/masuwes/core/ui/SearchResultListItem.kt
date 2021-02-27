package com.masuwes.core.ui

import android.util.Log
import android.view.View
import com.masuwes.core.R
import com.masuwes.core.databinding.ItemRvBinding
import com.masuwes.core.domain.model.Search
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

class SearchResultListItem(
    private val search: Search,
    private val onItemClick: OnItemClick
) : BindableItem<ItemRvBinding>() {

    override fun bind(viewBinding: ItemRvBinding, position: Int) {
        viewBinding.apply {
            search.poster_path?.let { imageListItem.loadImage(Constants.URL_IMAGE + it) }
            titleListItem.text = search.title
            rateListItem.text = search.vote_average.toString()
            dateListItem.text = search.release_date
            search.media_type?.let { Log.d("Search", it) }

            root.setOnClickListener {
                onItemClick.onClick(search)
            }
        }
    }

    interface OnItemClick {
        fun onClick(item: Search)
    }

    override fun getLayout(): Int = R.layout.item_rv

    override fun initializeViewBinding(view: View): ItemRvBinding = ItemRvBinding.bind(view)
}