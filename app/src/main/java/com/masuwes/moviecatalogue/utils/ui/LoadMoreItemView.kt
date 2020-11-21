package com.masuwes.moviecatalogue.utils.ui

import com.masuwes.moviecatalogue.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class LoadMoreItemView : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {}

    override fun getLayout(): Int = R.layout.loadmore_item_view
}