package com.example.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Activity of Storagelist.
 *
 * @author Nicole Gottschall
 * @since 2022-06-01
 */

public class StorageActivity extends AppCompatActivity {

    private static ListView listView;
    private ImageView addBtn;
    private Button switchBtn;
    private EditText tv;
    private DataBaseHelper dbHelper;
    private DataBaseHelper dbHelperExtern;

    /**
     * Initialization method of activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //create graphical interface
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        //change toolbar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);

        //create database
        dbHelper = new DataBaseHelper(StorageActivity.this, "storage.db");
        dbHelperExtern = new DataBaseHelper(StorageActivity.this, "shopping.db");

        assignVariables();
        registerClick();
        showAllProducts(dbHelper);
    }

    /**
     * Assign needed graphical elements to variables
     */
    private void assignVariables() {
        listView = (ListView) findViewById(R.id.lv);
        addBtn = (ImageView) findViewById(R.id.addBtn);
        switchBtn = (Button) findViewById(R.id.switchBtnToolbar);
        switchBtn.setText("Zur Einkaufsliste >");
        tv = (EditText) findViewById(R.id.tv);
    }

    /**
     * Show all current stored products in database in ListView.
     *
     * @param dbHelper
     */
    private void showAllProducts(DataBaseHelper dbHelper) {
        ArrayList<String> list = dbHelper.getAllAsList();
        Collections.reverse(list);
        Adapter adapter = new StorageAdapter(this, list, dbHelper, dbHelperExtern);
        listView.setAdapter(adapter);    }

    /**
     * Add OnClickListeners to the buttons.
     */
    private void registerClick() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = tv.getText().toString();

                if(Pattern.matches("s*", product)) { //ouputs toast if user did not add any title
                    Toast.makeText(StorageActivity.this, "Produktname fehlt.", Toast.LENGTH_SHORT).show();

                } else if(dbHelper.existsInDB(product)) { //outputs toast if user entered product that already exists in the list
                    Toast.makeText(StorageActivity.this, "Produkt befindet sich bereits auf dieser Liste.", Toast.LENGTH_LONG).show();

                } else {
                    dbHelper.addOne(product);
                    showAllProducts(dbHelper);
                }

                //Empty TextView
                tv.setText("");
            }
        });

        //button to switch to other activity
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StorageActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}