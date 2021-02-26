package com.masuwes.moviecatalogue.utils.ui

import android.view.View
import com.masuwes.core.databinding.LoadmoreItemViewBinding
import com.masuwes.moviecatalogue.R
import com.xwray.groupie.viewbinding.BindableItem

class LoadMoreItemView : BindableItem<LoadmoreItemViewBinding>() {

    override fun getLayout(): Int = R.layout.loadmore_item_view

    override fun initializeViewBinding(view: View): LoadmoreItemViewBinding = LoadmoreItemViewBinding.bind(view)

    override fun bind(viewBinding: LoadmoreItemViewBinding, position: Int) {}
}