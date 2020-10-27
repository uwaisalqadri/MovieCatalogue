package com.masuwes.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.domain.model.Movie
import com.masuwes.moviecatalogue.ui.detail.DetailActivity
import com.masuwes.moviecatalogue.utils.Constants
import com.masuwes.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.item_rv.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val listMovies = ArrayList<Movie>()

    fun setMoviesData(list: List<Movie>?) {
        if (list == null) return
        listMovies.clear()
        listMovies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listMovies[position]
        holder.bind(data)

        holder.itemView.setOnClickListener { view ->
            view.context.startActivity(
                Intent(view.context, DetailActivity::class.java)
                    .putExtra(DetailActivity.MOVIE_ID, listMovies[position].id)
            )
        }
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: Movie) {
            with(itemView) {
                image_list_item.loadImage(Constants.URL_IMAGE + movies.poster_path)
                title_list_item.text = movies.title
                rate_list_item.text = movies.vote_average.toString()
                date_list_item.text = movies.release_date
            }
        }
    }
}














