package com.masuwes.moviecatalogue.utils.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>: Fragment() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initViewBinding(inflater, container)
    }

    private fun initViewBinding(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean = false): View {
        if (!this::binding.isInitialized) {
            binding = getViewBinding(layoutInflater, container, attachToRoot)
        }else {
            throw IllegalArgumentException("Viewbinding not set")
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    abstract fun getViewBinding(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): VB

    protected open fun showLoading() {}

    protected open fun hideLoading() {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onViewReady()
    }

    private fun onViewReady() {
        initIntent()
        initUI()
        initAction()
        initProcess()
        initObservers()
    }

    protected open fun initIntent() {}

    protected open fun initUI() {}

    protected open fun initAction() {}

    protected open fun initProcess() {}

    protected open fun initObservers() {}

}