package com.example.pizzamaniya

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamania.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePageActivity : AppCompatActivity() {

    private lateinit var tvHello: TextView
    private lateinit var tvSubHello: TextView
    private lateinit var ivProfile: ImageView
    private lateinit var etSearch: EditText
    private lateinit var chipAll: TextView
    private lateinit var chipPizza: TextView
    private lateinit var chipSides: TextView
    private lateinit var chipDrinks: TextView
    private lateinit var rvFeatured: RecyclerView
    private lateinit var rvPopular: RecyclerView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fabCart: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page) // your XML layout

        // Initialize views
        tvHello = findViewById(R.id.tvHello)
        tvSubHello = findViewById(R.id.tvSubHello)
        ivProfile = findViewById(R.id.ivProfile)
        etSearch = findViewById(R.id.etSearch)
        chipAll = findViewById(R.id.chip_all)
        chipPizza = findViewById(R.id.chip_pizza)
        chipSides = findViewById(R.id.chip_sides)
        chipDrinks = findViewById(R.id.chip_drinks)
        rvFeatured = findViewById(R.id.rvFeatured)
        rvPopular = findViewById(R.id.rvPopular)
        bottomNav = findViewById(R.id.bottomNav)
        fabCart = findViewById(R.id.fabCart)

        // Example greeting, can be dynamic
        tvHello.text = "Hello, User!"
        tvSubHello.text = "What would you like to eat today?"

        // Profile click
        ivProfile.setOnClickListener {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            // Example: navigate to profile activity
            // startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Search action
        etSearch.setOnEditorActionListener { v, actionId, event ->
            val query = etSearch.text.toString()
            Toast.makeText(this, "Searching for: $query", Toast.LENGTH_SHORT).show()
            true
        }

        // Category chips
        val chipClickListener = { category: String ->
            Toast.makeText(this, "Selected: $category", Toast.LENGTH_SHORT).show()
            // Filter your RecyclerView items here
        }

        chipAll.setOnClickListener { chipClickListener("All") }
        chipPizza.setOnClickListener { chipClickListener("Pizza") }
        chipSides.setOnClickListener { chipClickListener("Sides") }
        chipDrinks.setOnClickListener { chipClickListener("Drinks") }

        // Setup RecyclerViews
        rvFeatured.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvFeatured.adapter = DummyAdapter(listOf("Pizza 1", "Pizza 2", "Pizza 3"))

        rvPopular.layoutManager = LinearLayoutManager(this)
        rvPopular.adapter = DummyAdapter(listOf("Pizza A", "Pizza B", "Pizza C", "Pizza D"))

        // Bottom navigation click
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                R.id.nav_orders -> Toast.makeText(this, "Orders clicked", Toast.LENGTH_SHORT).show()
                R.id.nav_profile -> Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            true
        }

        // Cart FAB
        fabCart.setOnClickListener {
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
    }

    // Dummy Adapter for RecyclerViews
    class DummyAdapter(private val items: List<String>) :
        RecyclerView.Adapter<DummyAdapter.DummyViewHolder>() {

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): DummyViewHolder {
            val textView = TextView(parent.context).apply {
                layoutParams = RecyclerView.LayoutParams(300, RecyclerView.LayoutParams.MATCH_PARENT)
                textSize = 16f
                setPadding(16, 16, 16, 16)
                setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
            }
            return DummyViewHolder(textView)
        }

        override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
            holder.textView.text = items[position]
        }

        override fun getItemCount(): Int = items.size

        class DummyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    }
}
