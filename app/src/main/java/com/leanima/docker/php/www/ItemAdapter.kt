import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leanima.R
import com.leanima.database.entity.Item

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.type + " " + '"' + item.name + '"'
        holder.itemPrice.text = "${item.price} грн"

        // Use the drawable resource or Glide
        val imageResId = holder.itemView.context.resources.getIdentifier(item.imageUrl, "drawable", holder.itemView.context.packageName)
        if (imageResId != 0) {
            holder.itemImage.setImageResource(imageResId)
        } else {
            Glide.with(holder.itemView.context).load(item.imageUrl).into(holder.itemImage)
        }
    }

    override fun getItemCount(): Int = items.size
}
