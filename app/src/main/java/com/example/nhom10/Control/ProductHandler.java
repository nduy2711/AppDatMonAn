package com.example.nhom10.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.nhom10.Model.Category;
import com.example.nhom10.Model.Product;
import com.example.nhom10.R;

import java.util.ArrayList;

public class ProductHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "qlbh";
    private static final int DB_VERSION = 1;
    private static final String PATH = "/data/data/com.example.nhom10/databases/qlbh.db";

    // Table Category
    private static final String CATEGORY_TABLE = "Category";
    private static final String CATEGORY_ID = "CategoryID";
    private static final String CATEGORY_NAME = "CategoryName";

    // Table MenuItem
    private static final String MENU_ITEM_TABLE = "Item";
    private static final String MENU_ITEM_ID = "MenuItemID";
    private static final String CATEGORY_ID_FK = "CategoryID";
    private static final String ITEM_IMAGE_ID = "ItemIdImage";
    private static final String ITEM_NAME = "ItemName";
    private static final String PRICE = "Price";

    public ProductHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ProductHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        String createCategoryTable = "CREATE TABLE IF NOT EXISTS " + CATEGORY_TABLE + " (" +
                CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CATEGORY_NAME + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createCategoryTable);
        Log.d("DB", "Category table created");

        String createItemTable = "CREATE TABLE IF NOT EXISTS " + MENU_ITEM_TABLE + " (" +
                MENU_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CATEGORY_ID_FK + " INTEGER, " +
                ITEM_NAME + " TEXT NOT NULL, " +
                PRICE + " REAL NOT NULL, " +
                ITEM_IMAGE_ID + " INTEGER, " +
                "FOREIGN KEY(" + CATEGORY_ID_FK + ") REFERENCES " + CATEGORY_TABLE + "(" + CATEGORY_ID + "))";
        sqLiteDatabase.execSQL(createItemTable);
        Log.d("DB", "MenuItem table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase(); // Mở kết nối với database
        ContentValues values = new ContentValues();

        values.put(CATEGORY_ID, category.getCategoryId()); // Thêm ID của category (nếu có)
        values.put(CATEGORY_NAME, category.getCategoryName()); // Thêm tên của category

        // Chèn vào bảng Category
        db.insert(CATEGORY_TABLE, null, values);

        // Đóng kết nối với database
        db.close();
    }

    public void insertItem(Product product) {
        SQLiteDatabase db = this.getWritableDatabase(); // Mở kết nối với database
        ContentValues values = new ContentValues();

        values.put(MENU_ITEM_ID, product.getMenuItemId()); // Thêm ID của món ăn
        values.put(CATEGORY_ID_FK, product.getCategoryIdFk()); // Thêm ID của danh mục (khóa ngoại)
        values.put(ITEM_NAME, product.getName()); // Thêm tên món ăn
        values.put(PRICE, product.getPrice()); // Thêm giá món ăn
        values.put(ITEM_IMAGE_ID, product.getImage()); // Thêm ID của hình ảnh món ăn

        // Chèn vào bảng Item (MenuItem)
        db.insert(MENU_ITEM_TABLE, null, values);

        // Đóng kết nối với database
        db.close();
    }

    public ArrayList<Product> getProductsByCategory(int categoryId) {
        ArrayList<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MENU_ITEM_TABLE + " WHERE " + CATEGORY_ID_FK + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(categoryId)}); // Chuyển đổi int sang String

        if (cursor.moveToFirst()) {
            do {
                int menuItemId = cursor.getInt(0); // MenuItemID
                int category = cursor.getInt(1); // CategoryID
                String name = cursor.getString(2); // Tên món ăn
                int image = cursor.getInt(3); // ID của hình ảnh món ăn
                double price = cursor.getDouble(4); // Giá món ăn
                productList.add(new Product(menuItemId, category, name, image, price)); // Khởi tạo đối tượng Product
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }

    public void initData() {
        // Thêm danh mục
        insertCategory(new Category(1, "Thịt nướng"));
        insertCategory(new Category(2, "Tokbokki"));
        insertCategory(new Category(3, "Lẩu"));
        insertCategory(new Category(4, "Ăn vặt"));

        // Thêm sản phẩm cho danh mục "C001" - Thịt nướng
        insertItem(new Product(1, 1, "Ba rọi", 100000, R.drawable.baroi));
        insertItem(new Product(2, 1, "Ba chỉ bò", 120000, R.drawable.bachibo));
        insertItem(new Product(3, 1, "Thăng bò", 130000, R.drawable.thangbo));
        insertItem(new Product(4, 1, "Ba chỉ cuộn nấm", 140000, R.drawable.bachibocuon));

        // Thêm sản phẩm cho danh mục "C002" - Tokbokki
        insertItem(new Product(5, 2, "Tokbokki truyền thống", 90000, R.drawable.tokbokkitruyenthong));
        insertItem(new Product(6, 2, "Tokbokki sốt cay", 95000, R.drawable.tokbokkisotcay));
        insertItem(new Product(7, 2, "Tokbokki sốt phô mai", 85000, R.drawable.tokbokkisotphomai));
        insertItem(new Product(8, 2, "Tokbokki chiên", 100000, R.drawable.tokbokkichienxu));

        // Thêm sản phẩm cho danh mục "C003" - Lẩu
        insertItem(new Product(9, 3, "Lẩu bò", 200000, R.drawable.laubo));
        insertItem(new Product(10, 3, "Lẩu kim chi", 190000, R.drawable.laukimchi));
        insertItem(new Product(11, 3, "Lẩu nấm", 210000, R.drawable.launam));
        insertItem(new Product(12, 3, "Lẩu tokbokki", 220000, R.drawable.lautokbokki));

        // Thêm sản phẩm cho danh mục "C004" - Ăn vặt
        insertItem(new Product(13, 4, "Chả cá hàn quốc", 50000, R.drawable.chacahanquoc));
        insertItem(new Product(14, 4, "Bánh mì trung quốc", 45000, R.drawable.banhmitrunghanquoc));
        insertItem(new Product(15, 4, "Manbu", 55000, R.drawable.manbu));
        insertItem(new Product(16, 4, "Dòi hàn quốc", 60000, R.drawable.doihanquoc));
    }

}
