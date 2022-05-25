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

public class MainActivity extends AppCompatActivity {

    /**
     * Attributes
     */
    private ShoppingList shopping;
    private Storage storing;
    private EditText shoppingTf;
    private ImageButton shoppingBtnAdd;
    private ImageButton shoppingBtnDelete;
    private ImageButton shoppingBtnStore;
    private ListView shoppinglist;
    private TextView tv;
    private ArrayAdapter<String> shoppingAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Erzeugen der benoetigten Elemente
         */
        shopping = new ShoppingList();
        storing = new Storage();

        /**
         * Zwischenlagerung der Elemente in Variablen, um ueber Variablennamen
         * auf die grafischen Elemente zugreifen zu könnne.
         */
        //Elemente der Shoppinglist
        shoppingTf = (EditText) findViewById(R.id.tfShopping);
        shoppingBtnAdd = (ImageButton) findViewById(R.id.addBtnShopping);
        shoppingBtnDelete = (ImageButton) findViewById(R.id.deleteBtnShopping);
        shoppingBtnStore = (ImageButton) findViewById(R.id.storeBtnShopping);
        shoppinglist = (ListView) findViewById(R.id.shoppinglist);
        tv = (TextView) findViewById(R.id.textView);


        /**
         * ListView's initialisieren
         */
        //Array Adapter will handle the data of the ListView
        shoppingAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, shopping.getProducts());
        //set Adapter to Shoppinglist
        shoppinglist.setAdapter(shoppingAdapter);
        //create list by supplying those view objects to the ListView

        /**
         * Aktionen, die durch Druecken der Buttons ausgeloest werden
         */
        /**
         * Add-Button bewirkt Aufruf der Methode zum Hinzufuegen
         * der Eingabe im Eingabefeld zur ShoppingList
         */
        shoppingBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = shoppingTf.getText().toString();
                shopping.addProduct(product, shopping, storing);
                //ArrayAdapter aktualisieren
                shoppingAdapter.notifyDataSetChanged();
                //Leere die Eingabe im Eingabefeld
                shoppingTf.setText("");
            }
        });

        /**
         * Aktionen der Buttons in ListView
         */

        /**
         * Delete-Button ShoppingList on Action bewirkt Aufruf der Methode zum Loeschen
         * des ausgewaehlten Produkts von der ShoppingList
         */
        shoppinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //make Toast
                Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_SHORT).show();
                //remove product - pass index of item that is going to be removed

            }
        });



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

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<String> productsList = shopping.getProducts();
        String[] products = productsList.toArray(new String[productsList.size()]);
        Toast.makeText(MainActivity.this, products[position], Toast.LENGTH_SHORT).show();
    }






}