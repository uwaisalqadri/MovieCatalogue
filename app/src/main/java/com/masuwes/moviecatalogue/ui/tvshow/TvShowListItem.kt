package com.masuwes.moviecatalogue.ui.tvshow

import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.loadImage
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_rv.view.*

class TvShowListItem(
    private val tvShows: TvShow,
    private val onItemClick: OnItemClick
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val poster = viewHolder.itemView.image_list_item
        val title = viewHolder.itemView.title_list_item
        val rate = viewHolder.itemView.rate_list_item
        val date = viewHolder.itemView.date_list_item

        tvShows.poster_path?.let { poster.loadImage(Constants.URL_IMAGE + it) }
        title.text = tvShows.name
        rate.text = tvShows.vote_average.toString()
        date.text = tvShows.first_air_date

        viewHolder.itemView.setOnClickListener {
            onItemClick.onClick(tvShows)
        }
    }

    override fun getLayout(): Int = R.layout.item_rv

    interface OnItemClick {
        fun onClick(item: TvShow)
    }
}