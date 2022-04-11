package com.masuwes.moviecatalogue.presentation.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.ActivitySplashScreenBinding

class SplashScreenActivity: AppCompatActivity(R.layout.activity_splash_screen) {

    private val handler = Handler(Looper.getMainLooper())
    private val binding: ActivitySplashScreenBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        handler.postDelayed({
            MainActivity.start(this)
            finish()
        }, 1000)

    }
}