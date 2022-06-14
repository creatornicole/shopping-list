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

<<<<<<< HEAD
public class MainActivity extends ActivityParent {


=======
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
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        DataBaseHelper dbHelper = new DataBaseHelper(MainActivity.this, "shopping.db");
        setDbHelper(dbHelper);

        assignVariables();
        registerClick();
        showAllProducts(dbHelper);
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
        shoppingList = new List(this);

        /**
         * Einrichten des Adapters
         */
        adapter = new ShoppingAdapter(this, getList());
        listView.setAdapter(adapter);
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb




    }

    public void showAllProducts(DataBaseHelper dbHelper) {
        Adapter adapter = new ShoppingAdapter(this, dbHelper.getAllAsList(), dbHelper);
        setAdapter(adapter);

        ListView lv = getListView();
        lv.setAdapter(adapter);
    }

    public void registerClick() {
        ImageButton addBtn = getAddBtn();
        Button switchBtn = getSwitchBtn();
        TextView tv = getTv();
        DataBaseHelper dbHelper = getDbHelper();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
<<<<<<< HEAD
            public void onClick(View view) {
                String product = tv.getText().toString();
                if(Pattern.matches("s*", product)) {
                    Toast.makeText(MainActivity.this, "Title is missing", Toast.LENGTH_SHORT).show();
                } else if(dbHelper.existsInDB(product)) {
                    Toast.makeText(MainActivity.this, "Already added Product to list", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.addOne(product);
                    showAllProducts(dbHelper);
                }
                //Textfeld wieder leeren
                tv.setText("");
            }
        });

        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StorageActivity.class);
                startActivity(i);
            }
        });
    }


=======
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
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb

}