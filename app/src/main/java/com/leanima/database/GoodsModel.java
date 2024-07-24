package com.leanima.database;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class GoodsModel {

    public static final class good {
        public String type;
        public String name;
        public String description;
        public int price;
        public int discount;
        public String imageURL;
        public String size;
    }

    private Connection conn = null;

    public GoodsModel() {
        // Конструктор може залишитися порожнім
    }

    private boolean openConnection() {
        try {
            // Зареєструйте драйвер JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Вказівка URL, імені користувача та пароля
            String url = "jdbc:sqlserver://192.168.170.111\\SQLEXPRESS;databaseName=leanima_db";
            String username = "marianpelekh";
            String password = "mH04122005Op";

            // Створення з'єднання
            conn = DriverManager.getConnection(url, username, password);
            return true;
        }  catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver не знайдено.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.err.println("Помилка з'єднання з базою даних. " + e);
            e.printStackTrace();
            return false;
        }
    }

    private boolean closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<good> getGoods() {
        if (!openConnection()) {
            Log.e("GoodsModel", "Failed to open database connection.");
            return null;
        }

        final String query = "SELECT * FROM goods";
        ArrayList<good> goods = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            // Перевірка, чи є дані у ResultSet
            if (!result.isBeforeFirst()) {
                Log.w("GoodsModel", "No data found in the database.");
            }

            while (result.next()) {
                good temp = new good();
                temp.type = result.getString("type");
                temp.name = result.getString("name");
                temp.description = result.getString("description");
                temp.price = result.getInt("price");
                temp.discount = result.getInt("discount");
                temp.imageURL = result.getString("imageURL");
                temp.size = result.getString("size");
                goods.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        // Перевірка, чи отримано дані
        if (goods.isEmpty()) {
            Log.w("GoodsModel", "The list of goods is empty.");
        }

        return goods;
    }
}
