package com.example.pizzamaniya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.pizzamania.R

class OrderTrackingActivity : AppCompatActivity() {

    private lateinit var tvOrderId: TextView
    private lateinit var tvOrderDate: TextView
    private lateinit var tvOrderStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_tracking)

        tvOrderId = findViewById(R.id.tvOrderId)
        tvOrderDate = findViewById(R.id.tvOrderDate)
        tvOrderStatus = findViewById(R.id.tvOrderStatus)

        // Sample Data — in real app, get from intent or database
        tvOrderId.text = "Order ID: #98765"
        tvOrderDate.text = "Date: 2025-09-16"
        tvOrderStatus.text = "Status: Preparing"
    }
}
