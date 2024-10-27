package com.example.nhom10.Model;

public class Product {
    private int menuItemId;   // Thuộc tính MenuItemID dạng int
    private int categoryIdFk; // Thuộc tính CategoryID (khóa ngoại) dạng int
    private String name;      // Tên món ăn
    private int image;        // ID của hình ảnh món ăn
    private double price;     // Giá của món ăn

    // Constructor
    public Product(int menuItemId, int categoryIdFk, String name, int image, double price) {
        this.menuItemId = menuItemId;  // Sử dụng kiểu int
        this.categoryIdFk = categoryIdFk; // Sử dụng kiểu int
        this.name = name;
        this.image = image;
        this.price = price;
    }

    // Getter và Setter cho menuItemId
    public int getMenuItemId() {  // Trả về kiểu int
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {  // Nhận vào kiểu int
        this.menuItemId = menuItemId;
    }

    // Getter và Setter cho categoryIdFk
    public int getCategoryIdFk() {  // Trả về kiểu int
        return categoryIdFk;
    }

    public void setCategoryIdFk(int categoryIdFk) {  // Nhận vào kiểu int
        this.categoryIdFk = categoryIdFk;
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho image
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    // Getter và Setter cho price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
