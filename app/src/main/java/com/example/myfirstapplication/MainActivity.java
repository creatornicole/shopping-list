package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private static ArrayList<String> products;
    private static ListView listView;
    private static Adapter adapter;
    private ImageButton addBtn;
    private ImageButton delBtn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Erzeugen und Zwischenlagern der benoetigten Elemente
         */
        products = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.lv);
        adapter = new Adapter(this, products);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        tv = (TextView) findViewById(R.id.tv);

        /**
         * Adapter für ListViews setzen
         */
        listView.setAdapter(adapter);

        /**
         * Aktionen, die durch Druecken der Buttons ausgeloest werden
         */
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
                    products.add(product);
                    Collections.reverse(products);
                    adapter.notifyDataSetChanged();
                    tv.setText("");
                }
            }
        });


        /**
         * Aktionen der Buttons in ListView
         */

        /**
         * Delete-Button ShoppingList on Action bewirkt Aufruf der Methode zum Loeschen
         * des ausgewaehlten Produkts von der ShoppingList
         */


        /**
         * OnListItemClick
         */



        /**
         * Store-Button ShoppingList on Action bewirkt Verschieben des ausgewaehlten Produkts
         * auf die Storage-Liste
         */


        /**
         * Add-Button Storage on Action bewirkt Aufruf der Methode zum Hinzufuegen
         * der Eingabe im Eingabefeld zur Storage-Liste
         */
        /**
         * Delete-Button Storage on Action bewirkt Aufruf der Methode zum Loeschen
         * des ausgewaehlten Produkts von der Storage-Liste
         */
        /**
         * Buy-Button Storage on Action bewirkt Verschieben des ausgewaehlten Produkts
         * auf die ShoppingList
         */




    }

    public static ArrayList<String> getList() {
        return products;
    }

    public static ListView getListView() {
        return listView;
    }




}