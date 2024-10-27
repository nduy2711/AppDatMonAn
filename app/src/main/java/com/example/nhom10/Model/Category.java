package com.example.nhom10.Model;

public class Category {
    private int categoryId;    // ID của danh mục dạng int
    private String categoryName;  // Tên của danh mục dạng String

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId; // Sử dụng kiểu int
        this.categoryName = categoryName;
    }

    // Getter và Setter cho categoryId
    public int getCategoryId() {  // Trả về kiểu int
        return categoryId;
    }

    public void setCategoryId(int categoryId) {  // Nhận vào kiểu int
        this.categoryId = categoryId;
    }

    // Getter và Setter cho categoryName
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
