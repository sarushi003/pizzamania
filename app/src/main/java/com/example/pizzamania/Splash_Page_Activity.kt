package com.example.pizzamania

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R

class SplashActivity : AppCompatActivity() {

    private val splashDelay = 2500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_page)

        val logo = findViewById<ImageView>(R.id.logoImage)
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        logo.startAnimation(anim)

        // Go to LoginPageActivity (note the different package: com.example.pizzamaniya)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, com.example.pizzamaniya.LoginPageActivity::class.java))
            finish()
        }, splashDelay)
    }
}