//package com.leanima.database;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.leanima.database.UserModel;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserViewModel extends ViewModel {
//
//    private GoodsModel goodsModel;
//    private MutableLiveData<String> userCreationStatus;
//    private MutableLiveData<List<String>> goodsList;
//
//    public UserViewModel() {
//        goodsModel = new GoodsModel();
//        userCreationStatus = new MutableLiveData<>();
//        goodsList = new MutableLiveData<>();
//    }
//
//    public LiveData<String> getUserCreationStatus() {
//        return userCreationStatus;
//    }
//
//    public LiveData<List<String>> getGoodsList() {
//        return goodsList;
//    }
//
////    public void createUser(String userID, String username) {
////        boolean success = userModel.createUser(userID, username);
////        userCreationStatus.setValue(success ? "User added successfully" : "Failed to add user");
////    }
//
//    public void loadGoods() {
//        ResultSet resultSet = goodsModel.getGoods();
//        List<String> goods = new ArrayList<>();
//        try {
//            while (resultSet.next()) {
//                goods.add(resultSet.getString("name"));
//            }
//            goodsList.setValue(goods);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
