package com.example.nhom10.Control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ItemHandler extends SQLiteOpenHelper {

    // Database name and version
    private static final String DB_NAME = "qlbh";
    private static final int DB_VERSION = 1;

    // Table names
    private static final String CATEGORY_TABLE = "Category";
    private static final String MENUITEM_TABLE = "MenuItem";

    // Columns for Category table
    private static final String CATEGORY_ID = "CategoryID";
    private static final String CATEGORY_NAME = "CategoryName";

    // Columns for MenuItem table
    private static final String MENUITEM_ID = "MenuItemID";
    private static final String MENUITEM_NAME = "ItemName";
    private static final String MENUITEM_PRICE = "Price";
    private static final String MENUITEM_CATEGORY_ID = "CategoryID"; // Foreign key

    private static final String PATH = "/data/data/com.example.nhom10/databases/qlbh.db";

    public ItemHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Category table
        String createCategoryTable = "CREATE TABLE IF NOT EXISTS " + CATEGORY_TABLE + " (" +
                CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CATEGORY_NAME + " TEXT NOT NULL)";
        db.execSQL(createCategoryTable);

        // Create MenuItem table
        String createMenuItemTable = "CREATE TABLE IF NOT EXISTS " + MENUITEM_TABLE + " (" +
                MENUITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MENUITEM_NAME + " TEXT NOT NULL, " +
                MENUITEM_PRICE + " REAL NOT NULL, " +
                MENUITEM_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + MENUITEM_CATEGORY_ID + ") REFERENCES " + CATEGORY_TABLE + "(" + CATEGORY_ID + "))";
        db.execSQL(createMenuItemTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MENUITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
        onCreate(db);
    }

    // Method to insert category
    public void insertCategory(String categoryName) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String insertSql = "INSERT OR IGNORE INTO " + CATEGORY_TABLE + " (" + CATEGORY_NAME + ") VALUES (?)";
        db.execSQL(insertSql, new String[]{categoryName});
        db.close();
    }

    // Method to insert menu item
    public void insertMenuItem(String itemName, double price, int categoryId) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String insertSql = "INSERT OR IGNORE INTO " + MENUITEM_TABLE + " (" + MENUITEM_NAME + ", " + MENUITEM_PRICE + ", " + MENUITEM_CATEGORY_ID + ") VALUES (?, ?, ?)";
        db.execSQL(insertSql, new Object[]{itemName, price, categoryId});
        db.close();
    }

    // Method to initialize sample data
    public void initData() {
        // Thêm các danh mục
        insertCategory("Thịt nướng");
        insertCategory("Tokbokki");
        insertCategory("Lẩu");
        insertCategory("Ăn vặt");

        // Thêm các món ăn cho từng danh mục
        // 1. Món trong danh mục "Thịt nướng" (CategoryID = 1)
        insertMenuItem("Sườn nướng", 100000, 1);
        insertMenuItem("Bò nướng", 120000, 1);
        insertMenuItem("Gà nướng", 80000, 1);
        insertMenuItem("Ba chỉ nướng", 90000, 1);

        // 2. Món trong danh mục "Tokbokki" (CategoryID = 2)
        insertMenuItem("Tokbokki truyền thống", 70000, 2);
        insertMenuItem("Tokbokki phô mai", 85000, 2);
        insertMenuItem("Tokbokki hải sản", 90000, 2);
        insertMenuItem("Tokbokki cay", 75000, 2);

        // 3. Món trong danh mục "Lẩu" (CategoryID = 3)
        insertMenuItem("Lẩu Thái", 150000, 3);
        insertMenuItem("Lẩu Kimchi", 140000, 3);
        insertMenuItem("Lẩu hải sản", 170000, 3);
        insertMenuItem("Lẩu nấm", 130000, 3);

        // 4. Món trong danh mục "Ăn vặt" (CategoryID = 4)
        insertMenuItem("Khoai tây chiên", 30000, 4);
        insertMenuItem("Phô mai que", 25000, 4);
        insertMenuItem("Xúc xích rán", 35000, 4);
        insertMenuItem("Bánh tráng trộn", 40000, 4);
    }
}
