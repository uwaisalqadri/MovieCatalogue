package com.masuwes.moviecatalogue.ui.favorite.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.DetailMovie
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.loadImage
import kotlinx.android.synthetic.main.item_rv.view.*

class FavMovieAdapter(
        private var context: Context,
        private val onItemClick: OnItemClick
) : PagedListAdapter<DetailMovie, FavMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailMovie>() {
            override fun areItemsTheSame(oldItem: DetailMovie, newItem: DetailMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DetailMovie, newItem: DetailMovie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false)
            )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        if (movie != null) {
            holder.poster.loadImage(Constants.URL_IMAGE + movie.poster_path)
            holder.title.text = movie.title
            holder.rate.text = movie.vote_average.toString()
            holder.date.text = movie.release_date
            holder.itemView.setOnClickListener {
                onItemClick.onClick(movie)
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster = itemView.image_list_item
        val title = itemView.title_list_item
        val rate = itemView.rate_list_item
        val date = itemView.date_list_item
    }

    interface OnItemClick {
        fun onClick(item: DetailMovie)
    }

}