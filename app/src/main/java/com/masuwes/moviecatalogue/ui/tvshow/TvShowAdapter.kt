package com.masuwes.moviecatalogue.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.model.TvShowModel
import com.masuwes.moviecatalogue.ui.detail.DetailActivity
import com.masuwes.moviecatalogue.utils.loadImage
import kotlinx.android.synthetic.main.item_rv.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val listShow = arrayListOf<TvShowModel>()

    fun setShows(shows: List<TvShowModel>?) {
        if (shows == null) return
        listShow.clear()
        listShow.addAll(shows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listShow[position]
        holder.bind(data)

        holder.itemView.setOnClickListener { view ->
            view.context.startActivity(
                Intent(view.context, DetailActivity::class.java)
                    .putExtra(DetailActivity.SHOW, listShow[position].tvId)
            )
        }
    }

    override fun getItemCount(): Int = listShow.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(show: TvShowModel) {
            with(itemView) {
                title_list_item.text = show.title
                date_list_item.text = show.date
                rate_list_item.text = show.rate
                image_list_item.loadImage(show.image)
            }
        }
    }
}

















