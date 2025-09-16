package com.example.pizzamaniya

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton

class OrderActivity : AppCompatActivity() {

    private lateinit var recyclerOrder: RecyclerView
    private lateinit var tvTotalOrder: TextView
    private lateinit var btnCheckout: MaterialButton
    private lateinit var adapter: OrderAdapter
    private lateinit var orders: MutableList<OrderItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        recyclerOrder = findViewById(R.id.recyclerOrder)
        tvTotalOrder = findViewById(R.id.tvTotalOrder)
        btnCheckout = findViewById(R.id.btnCheckout)

        // Sample data
        orders = mutableListOf(
            OrderItem("Pizza Margherita", 1, 2500),
            OrderItem("Chicken BBQ Pizza", 2, 3000),
            OrderItem("Veggie Supreme", 1, 1000)
        )

        adapter = OrderAdapter(this, orders) { total ->
            tvTotalOrder.text = "Total: Rs. $total"
        }

        recyclerOrder.layoutManager = LinearLayoutManager(this)
        recyclerOrder.adapter = adapter

        btnCheckout.setOnClickListener {
            val total = adapter.getTotalSelected()
            if (total == 0) {
                Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Checkout total: Rs. $total", Toast.LENGTH_LONG).show()
            }
        }
    }
}
