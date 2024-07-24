package com.leanima.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.leanima.R;
import com.leanima.database.entity.Item;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> items;

    public ItemAdapter(List<Item> items) {
        this.items = items;
        Log.i("ItemAdapter", "Item adapter called" + items);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.itemName.setText(item.getType() + " \"" + item.getName() + "\"");
        holder.itemPrice.setText(item.getPrice() + " грн");

        int imageResId = getResourceIdFromReference(item.getImageUrl(), holder.itemView.getContext());

        if (imageResId != 0) {
            Glide.with(holder.itemView.getContext())
                .load(imageResId)
                .into(holder.itemImage);
        } else {
            Glide.with(holder.itemView.getContext())
                .load(R.drawable.default_image) // або інший стандартний ресурс
                .into(holder.itemImage);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @SuppressLint("DiscouragedApi")
    private int getResourceIdFromReference(String reference, Context context) {
        String resourceName = reference.replace("@drawable/", "");
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        final TextView itemName;
        final TextView itemPrice;
        final ImageView itemImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }
}
