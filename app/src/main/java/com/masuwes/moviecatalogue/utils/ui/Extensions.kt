package com.masuwes.moviecatalogue.utils.ui

import android.content.Context
import android.os.Build
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.masuwes.core.R
import com.masuwes.core.utils.Constants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder_image)
        .fallback(R.drawable.placeholder_image)
        .into(this)
}

fun String.formatDate() : String {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O && this.isNotEmpty()) {
        val formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)
        val currentDate = LocalDate.parse(this, formatter)
        currentDate.format(DateTimeFormatter.ofPattern(Constants.FORMATTED_DATE))
    } else {
        if (this.isEmpty()) "Unknown Date" else this
    }
}

fun BottomNavigationView.onItemSelected(onItemSelectedListener: (item: MenuItem) -> Unit) {
    this.setOnNavigationItemSelectedListener {
        onItemSelectedListener.invoke(it)
        return@setOnNavigationItemSelectedListener true
    }
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