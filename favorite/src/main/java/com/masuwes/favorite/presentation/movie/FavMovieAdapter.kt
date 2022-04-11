package com.masuwes.favorite.presentation.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.core.databinding.ItemRvBinding
import com.masuwes.core.domain.model.DetailMovie
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.formatDate
import com.masuwes.moviecatalogue.utils.ui.loadImage

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
            ItemRvBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        if (movie != null) {
            holder.poster.loadImage(Constants.URL_IMAGE + movie.posterPath)
            holder.title.text = movie.title
            holder.rate.text = movie.voteAverage.toString()
            holder.date.text = movie.releaseDate.formatDate()
            holder.itemView.setOnClickListener {
                onItemClick.onClick(movie)
            }
        }
    }

    inner class ViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        val poster = binding.imageListItem
        val title = binding.titleListItem
        val rate = binding.rateListItem
        val date = binding.dateListItem
    }

    interface OnItemClick {
        fun onClick(item: DetailMovie)
    }

}