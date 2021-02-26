package com.masuwes.core.ui

import android.view.View
import com.masuwes.core.Constants
import com.masuwes.core.R
import com.masuwes.core.databinding.ItemRvBinding
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

class TvShowListItem(
    private val tvShows: TvShow,
    private val onItemClick: OnItemClick
) : BindableItem<ItemRvBinding>() {

    override fun bind(viewBinding: ItemRvBinding, position: Int) {
        viewBinding.apply {
            tvShows.poster_path?.let { imageListItem.loadImage(Constants.URL_IMAGE + it) }
            titleListItem.text = tvShows.name
            rateListItem.text = tvShows.vote_average.toString()
            dateListItem.text = tvShows.first_air_date

            root.setOnClickListener {
                onItemClick.onClick(tvShows)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_rv

    override fun initializeViewBinding(view: View): ItemRvBinding = ItemRvBinding.bind(view)

    interface OnItemClick {
        fun onClick(item: TvShow)
    }
}