package com.masuwes.moviecatalogue.utils.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        onViewReady()
    }

    private fun initViewBinding(){
        if (!this::binding.isInitialized){
            binding = getViewBinding()
            setContentView(binding.root)
        }
    }

    abstract fun getViewBinding(): VB

    protected open fun showLoading() {}

    protected open fun hideLoading() {}

    protected open fun onViewReady() {
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