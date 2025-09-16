package com.example.pizzamaniya

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminDashActivity : AppCompatActivity() {

    private lateinit var ordersCard: LinearLayout
    private lateinit var menuCard: LinearLayout
    private lateinit var customersCard: LinearLayout
    private lateinit var reportsCard: LinearLayout
    private lateinit var fabLogout: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dash) // your XML file

        // Initialize views
        ordersCard = findViewById(R.id.ordersCard)
        menuCard = findViewById(R.id.menuCard)
        customersCard = findViewById(R.id.customersCard)
        reportsCard = findViewById(R.id.reportsCard)
        fabLogout = findViewById(R.id.fabLogout)

        // Set click listeners
        ordersCard.setOnClickListener {
            // Open Orders activity
            Toast.makeText(this, "Orders clicked", Toast.LENGTH_SHORT).show()
            // Example: startActivity(Intent(this, OrdersActivity::class.java))
        }

        menuCard.setOnClickListener {
            // Open Menu activity
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
            val MenuActivity = Unit
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        customersCard.setOnClickListener {
            // Open Customers activity
            Toast.makeText(this, "Customers clicked", Toast.LENGTH_SHORT).show()
            // Example: startActivity(Intent(this, CustomersActivity::class.java))
        }

        reportsCard.setOnClickListener {
            // Open Reports activity
            Toast.makeText(this, "Reports clicked", Toast.LENGTH_SHORT).show()
            // Example: startActivity(Intent(this, ReportsActivity::class.java))
        }

        fabLogout.setOnClickListener {
            // Handle logout
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()
            // Example: finish() or redirect to login
            finish()
        }
    }
}
