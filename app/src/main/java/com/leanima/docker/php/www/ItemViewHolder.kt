import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leanima.R

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val itemImage: ImageView = view.findViewById(R.id.item_image)
    val itemName: TextView = view.findViewById(R.id.item_name)
    val itemPrice: TextView = view.findViewById(R.id.item_price)
}
