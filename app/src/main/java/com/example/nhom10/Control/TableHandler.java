package com.example.nhom10.Control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TableHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "qlbh";
    private static final int DB_VERSION = 1;
    private static final String PATH = "/data/data/com.example.nhom10/databases/qlbh.db";

    private static final String TABLE_NAME = "RestaurantTable";
    private static final String ID_COL_TABLE = "TableID";
    private static final String STATUS_COL_TABLE = "Status";
    private static final String TIME_COL_TABLE = "Time";
    private static final String DATE_COL_TABLE = "Date";
    private static final String CUSTOMERNAME_COL_TABLE = "CustomerName";
    private static final String EMPLOYEEID_COL_TABLE = "EmployeeID";

    public TableHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                ID_COL_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STATUS_COL_TABLE + " TEXT NOT NULL, " +
                TIME_COL_TABLE + " TEXT, " +
                DATE_COL_TABLE + " TEXT, " +
                CUSTOMERNAME_COL_TABLE + " TEXT, " +
                EMPLOYEEID_COL_TABLE + " INTEGER, " +
                "FOREIGN KEY (" + EMPLOYEEID_COL_TABLE + ") REFERENCES Employee(EmployeeID))";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
