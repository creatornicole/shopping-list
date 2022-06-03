package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    /**
     * Attributes
     */
    private static ListView listView;
    private ImageButton addBtn;
    private Button switchBtn;
    private TextView tv;
    private static List shoppingList;
    private static Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        shoppingList = new List(this);

        /**
         * Einrichten des Adapters
         */
        adapter = new ShoppingAdapter(this, getList());
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
                Intent i = new Intent(MainActivity.this, StorageActivity.class);
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
                shoppingList.addProduct(tv.getText().toString(), adapter);
                tv.setText("");
            }
        });
        /**
         * Store-Button ShoppingList on Action bewirkt Verschieben des ausgewaehlten Produkts
         * auf die Storage-Liste
         */
    }

    public static ListView getListView() {
        return listView;
    }

    public static ArrayList<String> getList() {
        return shoppingList.getProducts();
    }

}