import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamania.R

class MenuActivity : AppCompatActivity() {

    private lateinit var recyclerMenu: RecyclerView
    private lateinit var menuList: ArrayList<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        recyclerMenu = findViewById(R.id.recyclerMenu)

        menuList = arrayListOf(
            MenuItem("Pizza Margherita", "Classic cheese pizza.", 2500, R.drawable.menu_marpizza),
            MenuItem("Chicken BBQ Pizza", "BBQ chicken topping.", 3500, R.drawable.bbq),
            MenuItem("Veggie Supreme", "Mixed veggie pizza.", 2400, R.drawable.veg)
        )

        val adapter = MenuAdapter(menuList) { item ->
            // Open item details
            val intent = Intent(this, Item_Detail_Activity2::class.java)
            intent.putExtra("name", item.name)
            intent.putExtra("description", item.description)
            intent.putExtra("price", item.price)
            intent.putExtra("imageRes", item.imageRes)
            startActivity(intent)
        }

        recyclerMenu.layoutManager = GridLayoutManager(this, 2)
        recyclerMenu.adapter = adapter
    }
}
