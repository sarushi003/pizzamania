package com.example.pizzamaniya

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddMenuActivity : AppCompatActivity() {

    private lateinit var imgPizza: ImageView
    private lateinit var etPizzaName: TextInputEditText
    private lateinit var etPizzaDescription: TextInputEditText
    private lateinit var etPizzaPrice: TextInputEditText
    private lateinit var etCategory: TextInputEditText
    private lateinit var btnAddPizza: MaterialButton

    private var selectedImageUri: Uri? = null

    companion object {
        const val IMAGE_PICK_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu)

        // Initialize views using the correct IDs
        imgPizza = findViewById(R.id.ivMenuImage)
        etPizzaName = findViewById(R.id.etItemName)
        etPizzaDescription = findViewById(R.id.etDescription)
        etPizzaPrice = findViewById(R.id.etPrice)
        etCategory = findViewById(R.id.etCategory)
        btnAddPizza = findViewById(R.id.btnAddItem)

        // Click on image to pick from gallery
        imgPizza.setOnClickListener { pickImageFromGallery() }

        // Add item click
        btnAddPizza.setOnClickListener { addPizzaItem() }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            imgPizza.setImageURI(selectedImageUri)
        }
    }

    private fun addPizzaItem() {
        val name = etPizzaName.text.toString().trim()
        val description = etPizzaDescription.text.toString().trim()
        val priceText = etPizzaPrice.text.toString().trim()
        val category = etCategory.text.toString().trim()

        if (name.isEmpty() || description.isEmpty() || priceText.isEmpty() || category.isEmpty() || selectedImageUri == null) {
            Toast.makeText(this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceText.toDoubleOrNull()
        if (price == null) {
            Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
            return
        }

        val newPizza = Pizza(
            name = name,
            description = description,
            price = price,
            category = category,
            imageUri = selectedImageUri.toString()
        )

        MenuData.menuList.add(newPizza)
        Toast.makeText(this, "Pizza added successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}

// Updated Pizza data class with category
data class Pizza(
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageUri: String
)

// Singleton to hold menu items
object MenuData {
    val menuList = mutableListOf<Pizza>()
}
