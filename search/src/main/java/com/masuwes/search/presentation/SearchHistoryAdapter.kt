package com.masuwes.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.core.databinding.ItemRecentSearchBinding
import com.masuwes.core.domain.model.Search
import com.masuwes.core.utils.Constants
import com.masuwes.moviecatalogue.utils.ui.loadImage

class SearchHistoryAdapter(
    private val onItemClick: ((Search) -> Unit)?
): RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {

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
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(data)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(search: Search) {
            binding.apply {
                when(search.mediaType) {
                    Constants.TYPE_MOVIE -> {
                        imgRecentSearch.loadImage(Constants.URL_IMAGE + search.posterPath)
                        titleRecentSearch.text = search.title
                    }
                    Constants.TYPE_SHOW -> {
                        imgRecentSearch.loadImage(Constants.URL_IMAGE + search.posterPath)
                        titleRecentSearch.text = search.name
                    }
                }
            }
        }
    }

    interface OnItemClick {
        fun onClick(item: Search)
    }

}