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
    private static ArrayList<String> storage;
    private static ListView listView;
    private static StorageAdapter adapter;
    private ImageButton addBtn;
    private ImageButton delBtn;
    private Button switchBtn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        /**
         * Erzeugen und Zwischenlagern der benoetigten Elemente
         */
        storage = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.lv);
        adapter = new StorageAdapter(this, storage);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        switchBtn = (Button) findViewById(R.id.switchBtn);
        tv = (TextView) findViewById(R.id.tv);

        /**
         * Adapter für ListViews setzen
         */
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
                String product = tv.getText().toString();
                if(Pattern.matches("s*", product)) {
                    //ignoriere leere Eingaben
                } else {
                    storage.add(product);
                    Collections.reverse(storage);
                    adapter.notifyDataSetChanged();
                    tv.setText("");
                }
            }
        });
    }

    public static ArrayList<String> getList() {
        return storage;
    }

    public static ListView getListView() {
        return listView;
    }
}