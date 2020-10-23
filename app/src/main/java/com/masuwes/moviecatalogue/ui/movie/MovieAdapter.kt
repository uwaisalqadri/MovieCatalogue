package com.masuwes.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.model.MovieModel
import com.masuwes.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.item_rv.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int  {
        TODO("Not implemented yet")
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: MovieModel) {
            with(itemView) {
                title_list_item.text = movie.title
                date_list_item.text = movie.date
                rate_list_item.text = movie.rate
                image_list_item.loadImage(movie.image)
            }
        }
    }
}



















