package com.leanima;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leanima.database.ItemAdapter;
import com.leanima.database.ItemViewModel;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.itemContainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ItemAdapter(Collections.emptyList());
        recyclerView.setAdapter(itemAdapter);

        ItemViewModel itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        itemViewModel.getItems().observe(this, items -> {
            if (!items.isEmpty()) {
                itemAdapter.setItems(items);
                Log.i("MainActivity", "Items" + items);
            } else {
                Log.e("MainActivity", "Null of items");
            }
        });


        itemViewModel.loadItems();
    }
}
