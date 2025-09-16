package com.example.pizzamaniya

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AdminRegistrationActivity : AppCompatActivity() {

    private lateinit var etBranch: AutoCompleteTextView
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_registration) // your XML file

        // Initialize views
        etBranch = findViewById(R.id.etAdminBranch)
        etEmail = findViewById(R.id.etAdminEmail)
        etPassword = findViewById(R.id.etAdminPassword)
        etConfirmPassword = findViewById(R.id.etAdminConfirmPassword)
        btnRegister = findViewById(R.id.btnAdminRegister)
        tvLogin = findViewById(R.id.tvAdminLogin)

        // Register button click
        btnRegister.setOnClickListener {
            registerAdmin()
        }

        // Login text click
        tvLogin.setOnClickListener {
            // Navigate to login activity
            val AdminLoginActivity = Unit
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun registerAdmin() {
        val branch = etBranch.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        // Validation
        if (branch.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Save admin data to your backend / database
        Toast.makeText(this, "Admin registered successfully!", Toast.LENGTH_SHORT).show()

        // Optionally redirect to login or dashboard
        val AdminLoginActivity = Unit
        val intent = Intent(this, AdminLoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
