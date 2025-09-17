package com.example.pizzamaniya

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class UserRegistrationActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnSave: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        etName = findViewById(R.id.etName2)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener { handleRegister() }
    }

    private fun handleRegister() {
        val name = etName.text?.toString()?.trim().orEmpty()
        val email = etEmail.text?.toString()?.trim().orEmpty()
        val password = etPassword.text?.toString()?.trim().orEmpty()

        if (TextUtils.isEmpty(name)) {
            etName.error = "Enter full name"
            return
        }
        if (TextUtils.isEmpty(email)) {
            etEmail.error = "Enter email"
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Enter a valid email"
            return
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.error = "Enter password"
            return
        }
        if (password.length < 6) {
            etPassword.error = "Password must be at least 6 characters"
            return
        }

        Toast.makeText(this, "Account created for $name", Toast.LENGTH_SHORT).show()
        finish()
    }
}