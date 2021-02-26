package com.masuwes.core.ui

import android.view.View
import com.masuwes.core.Constants
import com.masuwes.core.R
import com.masuwes.core.databinding.ItemRvBinding
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

class MovieListItem(
    private val movies: Movie,
    private val onItemClick: OnItemClick
) : BindableItem<ItemRvBinding>() {

    override fun bind(viewBinding: ItemRvBinding, position: Int) {
        viewBinding.apply {
            movies.poster_path?.let { imageListItem.loadImage(Constants.URL_IMAGE + it) }
            titleListItem.text = movies.title
            rateListItem.text = movies.vote_average.toString()
            dateListItem.text = movies.release_date

            root.setOnClickListener {
                onItemClick.onClick(movies)
            }
        }
    }

    override fun initializeViewBinding(view: View): ItemRvBinding = ItemRvBinding.bind(view)

    override fun getLayout(): Int = R.layout.item_rv

    interface OnItemClick {
        fun onClick(item: Movie)
    }
}