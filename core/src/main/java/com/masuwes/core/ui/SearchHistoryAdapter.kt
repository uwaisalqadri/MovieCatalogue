package com.masuwes.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.core.databinding.ItemRecentSearchBinding
import com.masuwes.core.domain.model.Search
import com.masuwes.core.utils.Constants
import com.masuwes.core.utils.loadImage

class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRecentSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(search: Search) {
            binding.apply {
                when(search.media_type) {
                    "movie" -> {
                        imgRecentSearch.loadImage(Constants.URL_IMAGE + search.poster_path)
                        titleRecentSearch.text = search.title
                    }
                    "tv" -> {
                        imgRecentSearch.loadImage(Constants.URL_IMAGE + search.poster_path)
                        titleRecentSearch.text = search.name
                    }
                }
            }
        }
    }

}