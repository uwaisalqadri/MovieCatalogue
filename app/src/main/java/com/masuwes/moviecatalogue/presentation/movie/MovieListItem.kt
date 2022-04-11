package com.masuwes.moviecatalogue.presentation.movie

import android.view.View
import com.masuwes.core.R
import com.masuwes.core.databinding.ItemRvBinding
import com.masuwes.core.domain.model.Movie
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.formatDate
import com.masuwes.core.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem

class MovieListItem(
    private val movie: Movie,
    private val onItemClick: ((Movie) -> Unit)?
) : BindableItem<ItemRvBinding>() {

    override fun bind(viewBinding: ItemRvBinding, position: Int) {
        viewBinding.apply {
            movie.posterPath.let { imageListItem.loadImage(Constants.URL_IMAGE + it) }
            titleListItem.text = movie.title
            rateListItem.text = movie.voteAverage.toString()
            dateListItem.text = movie.releaseDate.formatDate()

            root.setOnClickListener {
                onItemClick?.invoke(movie)
            }
        }
    }

    override fun initializeViewBinding(view: View): ItemRvBinding = ItemRvBinding.bind(view)

    override fun getLayout(): Int = R.layout.item_rv
}