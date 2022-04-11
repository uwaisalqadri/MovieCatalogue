package com.masuwes.search.presentation

import android.view.View
import com.masuwes.core.R
import com.masuwes.core.databinding.ItemRvBinding
import com.masuwes.core.domain.model.Search
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.formatDate
import com.masuwes.core.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

class SearchResultListItem(
    private val search: Search,
    private val onItemClick: ((Search) -> Unit)?
) : BindableItem<ItemRvBinding>() {

    override fun bind(viewBinding: ItemRvBinding, position: Int) {
        viewBinding.apply {
            when(search.mediaType) {
                Constants.TYPE_MOVIE -> {
                    search.posterPath.let { imageListItem.loadImage(Constants.URL_IMAGE + it) }
                    titleListItem.text = search.title
                    rateListItem.text = search.voteAverage.toString()
                    dateListItem.text = search.releaseDate.formatDate()
                }
                Constants.TYPE_SHOW -> {
                    search.posterPath.let { imageListItem.loadImage(Constants.URL_IMAGE + it) }
                    titleListItem.text = search.name
                    rateListItem.text = search.voteAverage.toString()
                    dateListItem.text = search.firstAirDate.formatDate()
                }
            }

            root.setOnClickListener {
                onItemClick?.invoke(search)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_rv

    override fun initializeViewBinding(view: View): ItemRvBinding = ItemRvBinding.bind(view)
}