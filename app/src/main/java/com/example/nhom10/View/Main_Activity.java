package com.example.nhom10.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.nhom10.R;
import com.google.android.material.navigation.NavigationView;

public class Main_Activity extends AppCompatActivity {

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


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    FrameLayout frameLayout;
    GridView tableGridView;

    SQLiteDatabase sqLiteDatabase;

    int [] images = {R.drawable.table1, R.drawable.table2, R.drawable.table3, R.drawable.table4, R.drawable.table5, R.drawable.table6,
            R.drawable.table7, R.drawable.table8, R.drawable.table9, R.drawable.table10, R.drawable.table11, R.drawable.table12,
            R.drawable.table13, R.drawable.table14, R.drawable.table15, R.drawable.table16, R.drawable.table17, R.drawable.table18,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addControls();
        addEvents();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        TableGridAdapter adapter = new TableGridAdapter(this, images);
        tableGridView.setAdapter(adapter);

    }

    void addControls() {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        tableGridView = (GridView) findViewById(R.id.table_gridView);
    }

    void addEvents() {
        tableGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Position corresponds to the table ID (position 0 -> table 1, etc.)
                int tableId = position + 1;

                // Create intent to start Item_Activity
                Intent intent = new Intent(Main_Activity.this, Item_Activity.class);
                intent.putExtra("TABLE_ID", tableId);

                // Start the new activity
                startActivity(intent);
            }
        });
    }
}