import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzamania.R

class Item_Detail_Activity2 : AppCompatActivity() {

    private lateinit var imgPizza: ImageView
    private lateinit var tvPizzaName: TextView
    private lateinit var tvPizzaDescription: TextView
    private lateinit var tvPizzaPrice: TextView
    private lateinit var tvQuantity: TextView
    private lateinit var btnIncrease: Button
    private lateinit var btnDecrease: Button
    private lateinit var btnAddToCart: Button

    private var quantity = 1
    private var price = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail2)

        imgPizza = findViewById(R.id.imgPizza)
        tvPizzaName = findViewById(R.id.tvPizzaName)
        tvPizzaDescription = findViewById(R.id.tvPizzaDescription)
        tvPizzaPrice = findViewById(R.id.tvPizzaPrice)
        tvQuantity = findViewById(R.id.tvQuantity)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnDecrease = findViewById(R.id.btnDecrease)
        btnAddToCart = findViewById(R.id.btnAddToCart)

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val imageRes = intent.getIntExtra("imageRes", R.drawable.menu_marpizza)
        price = intent.getIntExtra("price", 0)

        tvPizzaName.text = name
        tvPizzaDescription.text = description
        imgPizza.setImageResource(imageRes)
        updatePrice()

        btnIncrease.setOnClickListener {
            quantity++
            tvQuantity.text = quantity.toString()
            updatePrice()
        }

        btnDecrease.setOnClickListener {
            if (quantity > 1) quantity--
            tvQuantity.text = quantity.toString()
            updatePrice()
        }

        btnAddToCart.setOnClickListener {
            Toast.makeText(this, "$quantity x $name added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updatePrice() {
        tvPizzaPrice.text = "Rs. ${price * quantity}"
    }
}
