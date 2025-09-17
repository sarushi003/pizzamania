package com.example.pizzamaniya

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText

class LoginPageActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnSignIn: MaterialButton
    private lateinit var btnGoogle: MaterialButton
    private lateinit var btnFacebook: MaterialButton

    private lateinit var btnRegisterUser: MaterialButton
    private lateinit var btnRegisterAdmin: MaterialButton

    private lateinit var chipGroupRoles: ChipGroup
    private lateinit var chipUser: Chip
    private lateinit var chipAdmin: Chip
    private lateinit var tvForgot: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        btnGoogle = findViewById(R.id.btnGoogle)
        btnFacebook = findViewById(R.id.btnFacebook)
        btnRegisterUser = findViewById(R.id.btnRegisterUser)
        btnRegisterAdmin = findViewById(R.id.btnRegisterAdmin)
        chipGroupRoles = findViewById(R.id.chipGroupRoles)
        chipUser = findViewById(R.id.chipUser)
        chipAdmin = findViewById(R.id.chipAdmin)
        tvForgot = findViewById(R.id.tvForgot)

        btnSignIn.setOnClickListener { handleSignIn() }
        btnGoogle.setOnClickListener { Toast.makeText(this, "Google login clicked", Toast.LENGTH_SHORT).show() }
        btnFacebook.setOnClickListener { Toast.makeText(this, "Facebook login clicked", Toast.LENGTH_SHORT).show() }

        btnRegisterUser.setOnClickListener {
            startActivity(Intent(this, UserRegistrationActivity::class.java))
        }
        btnRegisterAdmin.setOnClickListener {
            startActivity(Intent(this, AdminRegistrationActivity::class.java))
        }

        tvForgot.setOnClickListener {
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
        }

        chipGroupRoles.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chipUser -> Toast.makeText(this, "User selected", Toast.LENGTH_SHORT).show()
                R.id.chipAdmin -> Toast.makeText(this, "Admin selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleSignIn() {
        val email = etEmail.text?.toString()?.trim().orEmpty()
        val password = etPassword.text?.toString()?.trim().orEmpty()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            return
        }

        val role = when (chipGroupRoles.checkedChipId) {
            R.id.chipUser -> "User"
            R.id.chipAdmin -> "Admin"
            else -> ""
        }
        if (role.isEmpty()) {
            Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show()
            return
        }

        if (role == "User") {
            Toast.makeText(this, "User logged in successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomePageActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Admin logged in successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AdminDashActivity::class.java))
            finish()
        }
    }
}