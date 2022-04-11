package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.masuwes.core.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder_image)
        .fallback(R.drawable.placeholder_image)
        .into(this)

}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.snackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun TextView.searchListener(
    adapter: GroupAdapter<GroupieViewHolder>,
    onSearch: (String) -> Unit,
    onFinish: () -> Unit
) {
    var job: Job? = null
    addTextChangedListener { editable ->
        job?.cancel()
        job = MainScope().launch {
            delay(500L)
            editable?.let {
                adapter.clear()
                if (it.isNotEmpty()) {
                    onSearch(it.toString())
                } else {
                    onFinish()
                }
            }
        }
    }
}