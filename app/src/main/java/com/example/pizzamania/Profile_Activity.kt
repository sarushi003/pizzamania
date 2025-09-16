package com.example.pizzamaniya

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView
    private lateinit var txtUserName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtPhone: TextView
    private lateinit var txtAddress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // Make sure your layout file is named correctly

        // Initialize views
        imgProfile = findViewById(R.id.imgProfile)
        txtUserName = findViewById(R.id.txtUserName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPhone = findViewById(R.id.txtPhone)
        txtAddress = findViewById(R.id.txtAddress)

        // Load profile data
        loadUserProfile()
    }

    private fun loadUserProfile() {
        // In a real app, fetch this info from database or API
        val userName = "Sarushi Dewanarayana"
        val userEmail = "sarushi@example.com"
        val userPhone = "+94 71 234 5678"
        val userAddress = "123, Colombo, Sri Lanka"

        // Set text views
        txtUserName.text = userName
        txtEmail.text = userEmail
        txtPhone.text = userPhone
        txtAddress.text = userAddress

        // For profile image, you can load from drawable or URL (using Glide/Picasso)
        imgProfile.setImageResource(R.drawable.ic_user_avatar)
    }
}
