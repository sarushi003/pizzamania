package com.example.pizzamaniya

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamania.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePageActivity : AppCompatActivity() {

    private lateinit var tvHello: TextView
    private lateinit var tvSubHello: TextView
    private lateinit var ivProfile: ImageView
    private lateinit var etSearch: EditText

    private lateinit var chipGroup: ChipGroup
    private lateinit var chipAll: Chip
    private lateinit var chipPizza: Chip
    private lateinit var chipSides: Chip
    private lateinit var chipDrinks: Chip

    private lateinit var rvFeatured: RecyclerView
    private lateinit var rvPopular: RecyclerView
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fabCart: FloatingActionButton

    private lateinit var featuredAdapter: FeaturedAdapter
    private lateinit var popularAdapter: PopularAdapter

    private var currentCategory: String = "All"
    private var currentQuery: String = ""

    private val allItems: List<FoodItem> by lazy {
        listOf(
            FoodItem(1, "Margherita", "Pizza", isFeatured = true, isPopular = true),
            FoodItem(2, "Pepperoni", "Pizza", isFeatured = true, isPopular = true),
            FoodItem(3, "BBQ Chicken", "Pizza", isFeatured = true, isPopular = false),
            FoodItem(4, "Veggie Supreme", "Pizza", isFeatured = false, isPopular = true),
            FoodItem(5, "Garlic Bread", "Sides", isFeatured = false, isPopular = true),
            FoodItem(6, "Chicken Wings", "Sides", isFeatured = false, isPopular = true),
            FoodItem(7, "Cheesy Sticks", "Sides", isFeatured = false, isPopular = false),
            FoodItem(8, "Coke", "Drinks", isFeatured = false, isPopular = true),
            FoodItem(9, "Sprite", "Drinks", isFeatured = false, isPopular = false),
            FoodItem(10, "Fanta", "Drinks", isFeatured = false, isPopular = true),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Init views
        tvHello = findViewById(R.id.tvHello)
        tvSubHello = findViewById(R.id.tvSubHello)
        ivProfile = findViewById(R.id.ivProfile)
        etSearch = findViewById(R.id.etSearch)

        chipGroup = findViewById(R.id.chipGroup)
        chipAll = findViewById(R.id.chip_all)
        chipPizza = findViewById(R.id.chip_pizza)
        chipSides = findViewById(R.id.chip_sides)
        chipDrinks = findViewById(R.id.chip_drinks)

        rvFeatured = findViewById(R.id.rvFeatured)
        rvPopular = findViewById(R.id.rvPopular)
        bottomNav = findViewById(R.id.bottomNav)
        fabCart = findViewById(R.id.fabCart)

        // Greetings
        tvHello.text = "Hello, User!"
        tvSubHello.text = "What would you like to eat today?"

        // Profile
        ivProfile.setOnClickListener {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            // startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Search
        etSearch.setOnEditorActionListener { _, actionId, event ->
            val isEnter = event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN
            if (actionId == EditorInfo.IME_ACTION_SEARCH || isEnter) {
                currentQuery = etSearch.text.toString().trim()
                applyFilters()
                hideKeyboard()
                true
            } else false
        }

        // Chips - single selection
        chipAll.isChecked = true
        chipGroup.setOnCheckedStateChangeListener { _, _ ->
            currentCategory = when {
                chipPizza.isChecked -> "Pizza"
                chipSides.isChecked -> "Sides"
                chipDrinks.isChecked -> "Drinks"
                else -> "All"
            }
            applyFilters()
        }

        // RecyclerViews
        featuredAdapter = FeaturedAdapter(mutableListOf())
        rvFeatured.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvFeatured.adapter = featuredAdapter
        rvFeatured.setHasFixedSize(true)

        popularAdapter = PopularAdapter(mutableListOf())
        rvPopular.layoutManager = LinearLayoutManager(this)
        rvPopular.adapter = popularAdapter
        rvPopular.isNestedScrollingEnabled = false

        // BottomNav
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                R.id.nav_orders -> Toast.makeText(this, "Orders clicked", Toast.LENGTH_SHORT).show()
                R.id.nav_profile -> Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            true
        }

        // FAB
        fabCart.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        applyFilters()
    }

    private fun applyFilters() {
        val base = allItems.filter { item ->
            val matchCategory = currentCategory == "All" || item.category.equals(currentCategory, ignoreCase = true)
            val matchQuery = currentQuery.isBlank() || item.name.contains(currentQuery, ignoreCase = true)
            matchCategory && matchQuery
        }

        val featured = base.filter { it.isFeatured }
        val popular = base.filter { it.isPopular }

        featuredAdapter.submitList(if (featured.isNotEmpty()) featured else base.take(5))
        popularAdapter.submitList(if (popular.isNotEmpty()) popular else base)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etSearch.windowToken, 0)
    }

    // --------- Data + Adapters ----------

    data class FoodItem(
        val id: Int,
        val name: String,
        val category: String,
        val isFeatured: Boolean,
        val isPopular: Boolean
    )

    class FeaturedAdapter(private val items: MutableList<FoodItem>) :
        RecyclerView.Adapter<FeaturedAdapter.VH>() {

        inner class VH(val view: android.view.View) : RecyclerView.ViewHolder(view) {
            val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_featured_card, parent, false)
            return VH(view)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val item = items[position]
            holder.tvTitle.text = item.name
            holder.view.setOnClickListener {
                Toast.makeText(holder.view.context, "${item.name} clicked", Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount(): Int = items.size

        fun submitList(newList: List<FoodItem>) {
            items.clear()
            items.addAll(newList)
            notifyDataSetChanged()
        }
    }

    class PopularAdapter(private val items: MutableList<FoodItem>) :
        RecyclerView.Adapter<PopularAdapter.VH>() {

        inner class VH(val view: android.view.View) : RecyclerView.ViewHolder(view) {
            val tvTitle: TextView = view.findViewById(R.id.tvTitle)
            val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_popular_row, parent, false)
            return VH(view)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val item = items[position]
            holder.tvTitle.text = item.name
            holder.tvCategory.text = item.category
            holder.view.setOnClickListener {
                Toast.makeText(holder.view.context, "${item.name} clicked", Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount(): Int = items.size

        fun submitList(newList: List<FoodItem>) {
            items.clear()
            items.addAll(newList)
            notifyDataSetChanged()
        }
    }
}