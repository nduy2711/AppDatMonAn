package com.example.nhom10.Control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nhom10.Model.Employee;

public class LoginHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "qlbh";
    private static final int DB_VERSION = 1;
    private static final String PATH = "/data/data/com.example.nhom10/databases/qlbh.db";

    private static final String TABLE_NAME = "Employee";
    private static final String ID_COL_EMPLOYEE = "EmployeeID";
    private static final String USERNAME_COL_EMPLOYEE = "Username";
    private static final String PASSWORD_COL_EMPLOYEE = "Password";
    private static final String FULLNAME_COL_EMPLOYEE = "FullName";
    private static final String EMAIL_COL_EMPLOYEE = "Mail";
    private static final String PHONE_COL_EMPLOYEE = "Phone";
    private static final String CCCD_COL_EMPLOYEE = "CCCD";

    private static final String USERNAME_COL_LOGIN = "Username";
    private static final String PASSWORD_COL_LOGIN = "Password";

    public LoginHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        String createEmployeeTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                ID_COL_EMPLOYEE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COL_EMPLOYEE + " TEXT NOT NULL, " +
                PASSWORD_COL_EMPLOYEE + " TEXT NOT NULL, " +
                FULLNAME_COL_EMPLOYEE + " TEXT, " +
                EMAIL_COL_EMPLOYEE + " TEXT, " +
                PHONE_COL_EMPLOYEE + " TEXT, " +
                CCCD_COL_EMPLOYEE + " TEXT)";
        sqLiteDatabase.execSQL(createEmployeeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean checkLogin(String username, String password) {
        // Mở kết nối đến database
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        // Tạo truy vấn để kiểm tra thông tin đăng nhập
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME_COL_EMPLOYEE + "=? AND " + PASSWORD_COL_EMPLOYEE + "=?", new String[]{username, password});

        // Kiểm tra xem có kết quả nào trả về không
        boolean loggedIn = cursor.getCount() > 0;

        // Đóng con trỏ và cơ sở dữ liệu
        cursor.close();
        db.close();

        return loggedIn; // Trả về kết quả đăng nhập
    }


    public void insertEmployee(Employee employee) {
        // Mở kết nối đến database
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        // Tạo câu lệnh SQL để chèn dữ liệu vào bảng Employee
        String insertSql = "INSERT OR IGNORE INTO " + TABLE_NAME + " ("
                + FULLNAME_COL_EMPLOYEE + ", " + EMAIL_COL_EMPLOYEE + ", " + PHONE_COL_EMPLOYEE + ", "
                + CCCD_COL_EMPLOYEE + ", " + USERNAME_COL_EMPLOYEE + ", " + PASSWORD_COL_EMPLOYEE + ") VALUES (?, ?, ?, ?, ?, ?)";

        // Sử dụng câu lệnh SQLite để truyền dữ liệu vào các cột
        db.execSQL(insertSql, new String[]{
                employee.getFullName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getCccd(),
                employee.getUsername(),
                employee.getPassword()
        });

        // Đóng kết nối với database
        db.close();
    }

    public void initData() {
        // Tạo các đối tượng Employee với dữ liệu mẫu
        Employee emp1 = new Employee(1, "nhanvien1", "123", "Nguyễn Công Phượng", "congphuong@gmail.com", "012356789", "CCCD123");
        Employee emp2 = new Employee(2, "nhanvien2", "123", "Nguyễn Tiến Linh", "tienlinh@gmail.com", "1234556788", "CCCD456");

        // Chèn dữ liệu của từng Employee vào database
        insertEmployee(emp1);
        insertEmployee(emp2);
    }



}
