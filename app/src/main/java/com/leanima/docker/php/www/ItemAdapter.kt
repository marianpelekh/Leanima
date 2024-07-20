import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
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


        fun getResourceIdFromReference(reference: String, context: Context): Int {
            // Видалення префіксу "@drawable/"
            val resourceName = reference.replace("@drawable/", "")
            // Отримання ідентифікатора ресурсу
            return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        }

        // Приклад використання в адаптері RecyclerView
        val imageResId = getResourceIdFromReference(item.imageUrl, holder.itemView.context)

// Завантаження зображення
        if (imageResId != 0) {
            Glide.with(holder.itemView.context)
                .load(imageResId)
                .into(holder.itemImage)
        } else {
            // Обробка випадку, коли ресурс не знайдено
            Glide.with(holder.itemView.context)
                .load(R.drawable.default_image) // або інший стандартний ресурс
                .into(holder.itemImage)
        }

    }

    override fun getItemCount(): Int = items.size
}
