package com.leanima.database.entity;

public class Item {
    private String type;
    private String name;
    private String description;
    private int price;
    private int discount;
    private String imageUrl;
    private String size;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
}
