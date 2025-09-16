package com.example.pizzamaniya

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzamania.R
import com.google.android.material.button.MaterialButton

class OrderAdapter(
    private val context: Context,
    private val items: List<OrderItem>,
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = items[position]
        holder.tvNameQty.text = "${item.name} x${item.qty}"
        val rowTotal = item.price * item.qty
        holder.tvPrice.text = "Rs.$rowTotal"

        updateSelectState(holder, item)

        holder.btnSelect.setOnClickListener {
            item.selected = !item.selected
            notifyItemChanged(position)
            listener(calculateTotal())
        }
    }

    override fun getItemCount() = items.size

    private fun updateSelectState(holder: OrderViewHolder, item: OrderItem) {
        if (item.selected) {
            holder.btnSelect.text = "Selected"
            holder.itemView.alpha = 0.7f
        } else {
            holder.btnSelect.text = "Select"
            holder.itemView.alpha = 1f
        }
    }

    private fun calculateTotal(): Int {
        return items.filter { it.selected }.sumOf { it.price * it.qty }
    }

    fun getTotalSelected(): Int = calculateTotal()

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameQty: TextView = view.findViewById(R.id.tvOrderNameQty)
        val tvPrice: TextView = view.findViewById(R.id.tvOrderPrice)
        val btnSelect: MaterialButton = view.findViewById(R.id.btnSelectOrder)
    }
}
