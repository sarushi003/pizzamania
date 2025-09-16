import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton

class MenuAdapter(
    private val menuList: List<MenuItem>,
    private val itemClickListener: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMenuItem: ImageView = itemView.findViewById(R.id.ivMenuItem)
        val tvMenuName: TextView = itemView.findViewById(R.id.tvMenuName)
        val tvMenuPrice: TextView = itemView.findViewById(R.id.tvMenuPrice)
        val btnAddToCart: MaterialButton = itemView.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item_row, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = menuList[position]
        holder.ivMenuItem.setImageResource(item.imageRes)
        holder.tvMenuName.text = item.name
        holder.tvMenuPrice.text = "Rs. ${item.price}"

        holder.itemView.setOnClickListener {
            itemClickListener(item)
        }

        holder.btnAddToCart.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${item.name} added to cart", Toast.LENGTH_SHORT).show()
        }
    }
}
