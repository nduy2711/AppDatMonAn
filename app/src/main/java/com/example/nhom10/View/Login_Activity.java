package com.example.nhom10.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nhom10.Control.LoginHandler;
import com.example.nhom10.R;

public class Login_Activity extends AppCompatActivity {

    private static final String DB_NAME = "qlbh";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "NhanVien";
    private static final String ID_COL = "idNhanVien";
    private static final String NAME_COL = "TenNhanVien";
    private static final String EMAIL_COL = "Email";
    private static final String PHONE_COL = "SDT";
    private static final String CCCD_COL = "CCCD";
    private static final String USERNAME_COL = "TenTaiKhoan";
    private static final String PASSWORD_COL = "MatKhau";
    private static final String PATH = "/data/data/com.example.nhom1/database/qlbh.db";

    EditText edtUsername, edtPassword;
    Button btnLogin;

    LoginHandler loginHandler;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();
        loginHandler = new LoginHandler(this, DB_NAME, null, DB_VERSION);
        loginHandler.onCreate(sqLiteDatabase);
        loginHandler.initData();
        addEvents();

    }

    void addControls() {
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btn_Login);
    }

    void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (loginHandler.checkLogin(username, password)) {
                    startActivity(new Intent(Login_Activity.this, Main_Activity.class));
                    startActivity(getIntent());
                } else {
                    // Xử lý trường hợp đăng nhập không thành công
                }
            }
        });
    }
}