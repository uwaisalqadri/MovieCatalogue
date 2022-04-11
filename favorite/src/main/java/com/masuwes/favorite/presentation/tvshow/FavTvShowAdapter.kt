package com.masuwes.favorite.presentation.tvshow

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.core.databinding.ItemRvBinding
import com.masuwes.core.domain.model.DetailTvShow
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.formatDate
import com.masuwes.moviecatalogue.utils.ui.loadImage

class FavTvShowAdapter(
        private val context: Context,
        private val onItemClick: OnItemClick
) : PagedListAdapter<DetailTvShow, FavTvShowAdapter.ViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailTvShow>() {
            override fun areItemsTheSame(oldItem: DetailTvShow, newItem: DetailTvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DetailTvShow, newItem: DetailTvShow): Boolean {
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
        val tvShow = getItem(position)

        if (tvShow != null) {
            holder.poster.loadImage(Constants.URL_IMAGE + tvShow.posterPath)
            holder.title.text = tvShow.name
            holder.rate.text = tvShow.voteAverage.toString()
            holder.date.text = tvShow.firstAirDate.formatDate()
            holder.itemView.setOnClickListener {
                onItemClick.onClick(tvShow)
            }
        }
    }


    class ViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        val poster = binding.imageListItem
        val title = binding.titleListItem
        val rate = binding.rateListItem
        val date = binding.dateListItem
    }

    interface OnItemClick {
        fun onClick(item: DetailTvShow)
    }

}