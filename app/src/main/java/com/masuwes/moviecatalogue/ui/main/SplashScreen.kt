package com.masuwes.moviecatalogue.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.masuwes.moviecatalogue.R
import com.masuwes.moviecatalogue.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity(R.layout.activity_splash_screen) {

    private val handler = Handler(Looper.getMainLooper())
    private val binding: ActivitySplashScreenBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        handler.postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, 1000)

    }
}