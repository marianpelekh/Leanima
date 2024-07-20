package com.leanima;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leanima.database.ItemAdapter;
import com.leanima.database.ItemViewModel;
import com.leanima.database.entity.Item;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemViewModel itemViewModel;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.itemContainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ItemAdapter(Collections.emptyList());
        recyclerView.setAdapter(itemAdapter);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        itemViewModel.getItems().observe(this, items -> itemAdapter.setItems(items));

        itemViewModel.loadItems();
    }
}
