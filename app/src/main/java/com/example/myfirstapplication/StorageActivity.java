package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

<<<<<<< HEAD
public class StorageActivity extends ActivityParent {
=======
public class StorageActivity extends AppCompatActivity {

    /**
     * Attributes
     */
    private static ListView listView;
    private ImageButton addBtn;
    private Button switchBtn;
    private TextView tv;
    private static List storageList;
    private static Adapter adapter;
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

<<<<<<< HEAD
        DataBaseHelper dbHelper = new DataBaseHelper(StorageActivity.this, "storage.db");
        setDbHelper(dbHelper);

        assignVariables();
        registerClick();
        showAllProducts(dbHelper);
    }

    public void showAllProducts(DataBaseHelper dbHelper) {
        Adapter adapter = new StorageAdapter(this, dbHelper.getAllAsList(), dbHelper);
        setAdapter(adapter);

        ListView lv = getListView();
        lv.setAdapter(adapter);
    }

    public void registerClick() {
        ImageButton addBtn = getAddBtn();
        Button switchBtn = getSwitchBtn();
        TextView tv = getTv();
        DataBaseHelper dbHelper = getDbHelper();
=======
        /**
         * Zuweisung der benoetigten grafischen Elemente zu Variablen
         */
        listView = (ListView) findViewById(R.id.lv);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        switchBtn = (Button) findViewById(R.id.switchBtn);
        tv = (TextView) findViewById(R.id.tv);

        /**
         * Erzeugen der Liste
         */
        storageList = new List(this);

        /**
         * Einrichten des Adapters
         */
        adapter = new StorageAdapter(this, getList());
        listView.setAdapter(adapter);
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
<<<<<<< HEAD
            public void onClick(View view) {
                String product = tv.getText().toString();
                if(Pattern.matches("s*", product)) {
                    Toast.makeText(StorageActivity.this, "Title is missing", Toast.LENGTH_SHORT).show();
                } else if(dbHelper.existsInDB(product)) {
                    Toast.makeText(StorageActivity.this, "Already added Product to list", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.addOne(product);
                    showAllProducts(dbHelper);
                }
                //Textfeld wieder leeren
=======
            public void onClick(View v) {
                storageList.addProduct(tv.getText().toString(), adapter);
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb
                tv.setText("");
            }
        });

<<<<<<< HEAD
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StorageActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
=======
    public static ListView getListView() {
        return listView;
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb
    }

    public static ArrayList<String> getList() {
        return storageList.getProducts();
    }
}