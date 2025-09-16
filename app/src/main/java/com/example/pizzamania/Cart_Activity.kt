package com.example.pizzamania

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Cart_Activity : AppCompatActivity() {

    // Sample cart items (In real apps, this comes from DB / API / SharedPreferences)
    private val cartItems = listOf(
        CartItem("BBQ-Chicken Pizza", 2, 10.00),
        CartItem("Cheese Lovers Pizza", 1, 8.50),
        CartItem("Veggie Delight", 3, 12.75)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // 🔹 UI references
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val btnNext = findViewById<ImageButton>(R.id.btnNext)
        val btnCheckout = findViewById<Button>(R.id.btnCheckout)
        val layoutCartItems = findViewById<LinearLayout>(R.id.layoutCartItems)

        // 🔹 Load cart items dynamically
        loadCartItems(layoutCartItems)

        // 🔹 Back button → finish activity
        btnBack.setOnClickListener {
            finish()
        }

        // 🔹 Next button → Go to CheckoutActivity
        btnNext.setOnClickListener {
            val intent = Intent(this, Check_outActivity::class.java)
            startActivity(intent)
        }

        // 🔹 Checkout button → Go to CheckoutActivity
        btnCheckout.setOnClickListener {
            if (cartItems.isNotEmpty()) {
                val intent = Intent(this, Check_outActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to dynamically add cart items into layout
    private fun loadCartItems(layout: LinearLayout) {
        layout.removeAllViews() // clear before adding

        for (item in cartItems) {
            val itemLayout = layoutInflater.inflate(R.layout.cart_item_row, layout, false)

            val tvNameQty = itemLayout.findViewById<TextView>(R.id.tvNameQty)
            val tvPrice = itemLayout.findViewById<TextView>(R.id.tvPrice)

            tvNameQty.text = "${item.name} x ${item.quantity}"
            tvPrice.text = "$${String.format("%.2f", item.price * item.quantity)}"

            layout.addView(itemLayout)
        }
    }
}

// 🔹 Data class for cart items
data class CartItem(
    val name: String,
    val quantity: Int,
    val price: Double
)
