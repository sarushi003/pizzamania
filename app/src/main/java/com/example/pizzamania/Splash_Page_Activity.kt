package com.example.pizzamania

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_page)

        val logo = findViewById<ImageView>(R.id.logoImage)
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        logo.startAnimation(anim)

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 2500) // 2.5s delay
    }
}