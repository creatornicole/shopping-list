package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

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

        /**
         * Aktionen, die durch Druecken der Buttons ausgeloest werden
         */
        /**
         * Switch-Button zum Wechseln der Liste
         */
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StorageActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        /**
         * Add-Button bewirkt Aufruf der Methode zum Hinzufuegen
         * der Eingabe im Eingabefeld zur ShoppingList
         */
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageList.addProduct(tv.getText().toString(), adapter);
                tv.setText("");
            }
        });
    }

    public static ListView getListView() {
        return listView;
    }

    public static ArrayList<String> getList() {
        return storageList.getProducts();
    }
}