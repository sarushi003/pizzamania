package com.example.pizzamaniya

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton

class CheckoutActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvTotal: TextView
    private lateinit var btnPlaceOrder: MaterialButton
    private lateinit var rbCash: RadioButton
    private lateinit var rbCard: RadioButton
    private lateinit var radioGroupPayment: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out) // your XML layout

        // Initialize views
        toolbar = findViewById(R.id.toolbarCheckout)
        tvTotal = findViewById(R.id.tvTotal)
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)
        rbCash = findViewById(R.id.rbCash)
        rbCard = findViewById(R.id.rbCard)
        radioGroupPayment = findViewById(R.id.ivPizza)

        // Setup Toolbar back button
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        // Set total dynamically if needed
        val totalAmount = intent.getIntExtra("TOTAL_AMOUNT", 2200) // default 2200
        tvTotal.text = "Total: Rs. $totalAmount"

        // Place order click
        btnPlaceOrder.setOnClickListener {
            placeOrder(totalAmount)
        }
    }

    private fun placeOrder(total: Int) {
        // Get selected payment method
        val selectedPayment = when (radioGroupPayment.checkedRadioButtonId) {
            R.id.rbCash -> "Cash on Delivery"
            R.id.rbCard -> "Credit/Debit Card"
            else -> "Cash on Delivery"
        }

        // TODO: Integrate with backend / Firebase to save order

        // Show confirmation
        Toast.makeText(
            this,
            "Order placed successfully!\nTotal: Rs. $total\nPayment: $selectedPayment",
            Toast.LENGTH_LONG
        ).show()

        // Optionally, redirect to Home or Order summary
        finish()
    }
}
