package com.example.pizzamaniya

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton

class OrderSummaryActivity : AppCompatActivity() {

    private lateinit var tvTotalAmount: TextView

    // Quantities
    private var qty1 = 1
    private var qty2 = 1
    private var qty3 = 1

    // Prices
    private val price1 = 2500
    private val price2 = 3500
    private val price3 = 2400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_sum)

        tvTotalAmount = findViewById(R.id.tvTotalAmount)

        // Buttons and Quantity TextViews
        val btnDecrease1 = findViewById<MaterialButton>(R.id.btnDecrease1)
        val btnIncrease1 = findViewById<MaterialButton>(R.id.btnIncrease1)
        val tvQuantity1 = findViewById<TextView>(R.id.tvQuantity1)

        val btnDecrease2 = findViewById<MaterialButton>(R.id.btnDecrease2)
        val btnIncrease2 = findViewById<MaterialButton>(R.id.btnIncrease2)
        val tvQuantity2 = findViewById<TextView>(R.id.tvQuantity2)

        val btnDecrease3 = findViewById<MaterialButton>(R.id.btnDecrease3)
        val btnIncrease3 = findViewById<MaterialButton>(R.id.btnIncrease3)
        val tvQuantity3 = findViewById<TextView>(R.id.tvQuantity3)

        // Set click listeners for item 1
        btnDecrease1.setOnClickListener {
            if (qty1 > 1) qty1--
            tvQuantity1.text = qty1.toString()
            updateTotal()
        }
        btnIncrease1.setOnClickListener {
            qty1++
            tvQuantity1.text = qty1.toString()
            updateTotal()
        }

        // Item 2
        btnDecrease2.setOnClickListener {
            if (qty2 > 1) qty2--
            tvQuantity2.text = qty2.toString()
            updateTotal()
        }
        btnIncrease2.setOnClickListener {
            qty2++
            tvQuantity2.text = qty2.toString()
            updateTotal()
        }

        // Item 3
        btnDecrease3.setOnClickListener {
            if (qty3 > 1) qty3--
            tvQuantity3.text = qty3.toString()
            updateTotal()
        }
        btnIncrease3.setOnClickListener {
            qty3++
            tvQuantity3.text = qty3.toString()
            updateTotal()
        }

        // Initial total
        updateTotal()
    }

    private fun updateTotal() {
        val total = qty1 * price1 + qty2 * price2 + qty3 * price3
        tvTotalAmount.text = "Total: Rs. $total"
    }
}
