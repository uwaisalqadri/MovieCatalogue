package com.masuwes.moviecatalogue.presentation.tvshow

import android.view.View
import com.masuwes.core.R
import com.masuwes.core.databinding.ItemRvBinding
import com.masuwes.core.domain.model.TvShow
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.formatDate
import com.masuwes.moviecatalogue.utils.ui.loadImage
import com.xwray.groupie.viewbinding.BindableItem

class TvShowListItem(
    private val tvShow: TvShow,
    private val onClick: ((TvShow) -> Unit)?
) : BindableItem<ItemRvBinding>() {

    override fun bind(viewBinding: ItemRvBinding, position: Int) {
        viewBinding.apply {
            tvShow.posterPath.let { imageListItem.loadImage(Constants.URL_IMAGE + it) }
            titleListItem.text = tvShow.name
            rateListItem.text = tvShow.voteAverage.toString()
            dateListItem.text = tvShow.firstAirDate.formatDate()

            root.setOnClickListener {
                onClick?.invoke(tvShow)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_rv

    override fun initializeViewBinding(view: View): ItemRvBinding = ItemRvBinding.bind(view)
}