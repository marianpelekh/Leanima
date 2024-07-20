package com.leanima.database;

import java.sql.*;
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/database", "user", "pw");
            return true;
        } catch (Exception e) {
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
        if (!openConnection())
            return null;

        final String query = "SELECT * FROM goods";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);

            ArrayList<good> goods = new ArrayList<good>();

            ResultSet result = stmt.getResultSet();
            while(result.next()) {
                good temp = new good();
                temp.type = result.getNString("type");
                temp.name = result.getNString("name");
                temp.description = result.getNString("description");
                temp.price = result.getInt("price");
                temp.discount = result.getInt("discount");
                temp.imageURL = result.getNString("imageURL");
                temp.size = result.getNString("size");
                goods.add(temp);
            }
            return goods;
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return null;
        }
    }
}
