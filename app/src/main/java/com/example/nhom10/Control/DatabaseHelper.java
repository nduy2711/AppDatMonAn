package com.example.nhom10.Control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final String DB_NAME = "qlbh";
    private static final int DB_VERSION = 1;
    private static final String PATH = "/data/data/com.example.nhom1/databases/qlbh.db";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createEmployeeTable = "CREATE TABLE Employee (" +
                "EmployeeID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Username TEXT, " +
                "Password TEXT, " +
                "FullName TEXT, " +
                "Mail TEXT, " +
                "Phone TEXT, " +
                "CCCD TEXT)";
        db.execSQL(createEmployeeTable);

        String createTableTable = "CREATE TABLE `Table` (" +
                "TableID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TableNumber INTEGER, " +
                "Status TEXT, " +
                "Time TEXT, " +
                "Date TEXT, " +
                "CustomerName TEXT, " +
                "EmployeeID INTEGER, " +
                "FOREIGN KEY(EmployeeID) REFERENCES Employee(EmployeeID))";
        db.execSQL(createTableTable);

        // Create Category Table
        String createCategoryTable = "CREATE TABLE Category (" +
                "CategoryID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CategoryName TEXT)";
        db.execSQL(createCategoryTable);

        // Create MenuItem Table
        String createMenuItemTable = "CREATE TABLE MenuItem (" +
                "MenuItemID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CategoryID INTEGER, " +
                "ItemName TEXT, " +
                "Price REAL, " +
                "FOREIGN KEY(CategoryID) REFERENCES Category(CategoryID))";
        db.execSQL(createMenuItemTable);

        // Create OrderItem Table
        String createOrderItemTable = "CREATE TABLE OrderItem (" +
                "OrderID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MenuItemID INTEGER, " +
                "Quantity INTEGER, " +
                "Price REAL, " +
                "TableID INTEGER, " +
                "FOREIGN KEY(MenuItemID) REFERENCES MenuItem(MenuItemID), " +
                "FOREIGN KEY(TableID) REFERENCES Table(TableID))";
        db.execSQL(createOrderItemTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Employee");
        db.execSQL("DROP TABLE IF EXISTS `Table`");
        db.execSQL("DROP TABLE IF EXISTS Category");
        db.execSQL("DROP TABLE IF EXISTS MenuItem");
        db.execSQL("DROP TABLE IF EXISTS OrderItem");
        onCreate(db);
    }
}
