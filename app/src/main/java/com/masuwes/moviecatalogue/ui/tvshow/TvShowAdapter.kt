package com.masuwes.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.TvShow
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.item_rv.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val listShows = ArrayList<TvShow>()

    fun setShows(list: List<TvShow>?) {
        if (list == null) return
        listShows.clear()
        listShows.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listShows[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listShows.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(shows: TvShow) {
            with(itemView) {
                image_list_item.loadImage(Constants.URL_IMAGE + shows.poster_path)
                title_list_item.text = shows.name
                rate_list_item.text = shows.vote_average.toString()
                date_list_item.text = shows.first_air_date
            }
        }
    }
}