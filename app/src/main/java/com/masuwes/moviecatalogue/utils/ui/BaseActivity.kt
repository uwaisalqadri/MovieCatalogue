package com.masuwes.moviecatalogue.utils.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    fun setFragment(viewRes: Int, fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(viewRes, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        // Commit the transaction
        transaction.commit()
    }

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