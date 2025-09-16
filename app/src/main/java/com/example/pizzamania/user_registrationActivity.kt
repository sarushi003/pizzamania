package com.example.pizzamaniya

import android.R
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class registerActivity : AppCompatActivity() {
    private var etName: TextInputEditText? = null
    private var etEmail: TextInputEditText? = null
    private var etPassword: TextInputEditText? = null
    private var btnSave: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // match with your XML filename

        // Initialize views
        etName = findViewById<TextInputEditText>(R.id.etName2)
        etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        btnSave = findViewById<MaterialButton>(R.id.btnSave)

        // Button click
        btnSave.setOnClickListener(View.OnClickListener { handleRegister() })
    }

    private fun handleRegister() {
        val name = if (etName!!.text != null) etName!!.text.toString().trim { it <= ' ' } else ""
        val email = if (etEmail!!.text != null) etEmail!!.text.toString().trim { it <= ' ' } else ""
        val password =
            if (etPassword!!.text != null) etPassword!!.text.toString().trim { it <= ' ' } else ""

        // Basic validation
        if (TextUtils.isEmpty(name)) {
            etName!!.error = "Enter full name"
            return
        }
        if (TextUtils.isEmpty(email)) {
            etEmail!!.error = "Enter email"
            return
        }
        if (TextUtils.isEmpty(password)) {
            etPassword!!.error = "Enter password"
            return
        }
        if (password.length < 6) {
            etPassword!!.error = "Password must be at least 6 characters"
            return
        }

        // TODO: connect to Firebase/Auth or API
        Toast.makeText(this, "Account created for $name", Toast.LENGTH_SHORT).show()
    }
}