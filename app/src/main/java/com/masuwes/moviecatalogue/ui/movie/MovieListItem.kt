package com.masuwes.moviecatalogue.ui.movie

import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.loadImage
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_rv.view.*

class MovieListItem(
    private val movies: Movie,
    private val onItemClick: OnItemClick
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val poster = viewHolder.itemView.image_list_item
        val title = viewHolder.itemView.title_list_item
        val rate = viewHolder.itemView.rate_list_item
        val date = viewHolder.itemView.date_list_item

        movies.poster_path?.let { poster.loadImage(Constants.URL_IMAGE + it) }
        title.text = movies.title
        rate.text = movies.vote_average.toString()
        date.text = movies.release_date

        viewHolder.itemView.setOnClickListener {
            onItemClick.onClick(movies)
        }
    }

    override fun getLayout(): Int = R.layout.item_rv

    interface OnItemClick {
        fun onClick(item: Movie)
    }
}