package com.leanima.database;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leanima.database.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<List<Item>> items = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public LiveData<List<Item>> getItems() {
        return items;
    }

    public void loadItems() {
        Future<?> future = executorService.submit(new LoadItemsTask());
        // Optionally: Add code to handle `future` if you want to cancel or wait for completion
    }

    private class LoadItemsTask implements Runnable {
        @Override
        public void run() {
            Log.i("Load Items Task", "Method is called");
            GoodsModel model = new GoodsModel();
            ArrayList<GoodsModel.good> goods = model.getGoods();
            List<Item> itemList = new ArrayList<>();
            if (goods != null) {
                for (GoodsModel.good good : goods) {
                    Item item = new Item();
                    item.setType(good.type);
                    item.setName(good.name);
                    item.setDescription(good.description);
                    item.setPrice(good.price);
                    item.setDiscount(good.discount);
                    item.setImageUrl(good.imageURL);
                    item.setSize(good.size);
                    itemList.add(item);
                }
            }
            items.postValue(itemList); // Update LiveData on the main thread
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
    }
}
