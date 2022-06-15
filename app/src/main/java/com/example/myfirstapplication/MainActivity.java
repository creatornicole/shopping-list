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
<<<<<<< HEAD
    private EditText tv;
    private static Adapter adapter;
    private DataBaseHelper dbHelper;
=======
<<<<<<< HEAD
    private EditText tv;
    private static Adapter adapter;
    private DataBaseHelper dbHelper;
=======
    private TextView tv;
    private static List shoppingList;
    private static Adapter adapter;
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        dbHelper = new DataBaseHelper(MainActivity.this, "shopping.db");

=======
<<<<<<< HEAD
        dbHelper = new DataBaseHelper(MainActivity.this, "shopping.db");

=======
<<<<<<< HEAD
        DataBaseHelper dbHelper = new DataBaseHelper(MainActivity.this, "shopping.db");
        setDbHelper(dbHelper);
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c

        assignVariables();
        registerClick();
        showAllProducts(dbHelper);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c
    }

    /**
     * Weisst die benoetigten grafischen Elemente zu Variablen zu
     */
    public void assignVariables() {
<<<<<<< HEAD
=======
=======
=======
        /**
         * Zuweisung der benoetigten grafischen Elemente zu Variablen
         */
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c
        listView = (ListView) findViewById(R.id.lv);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        switchBtn = (Button) findViewById(R.id.switchBtn);
        tv = (EditText) findViewById(R.id.tv);
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c
    public void showAllProducts(DataBaseHelper dbHelper) {
        Adapter adapter = new ShoppingAdapter(this, dbHelper.getAllAsList(), dbHelper);
        listView.setAdapter(adapter);
    }

    public void registerClick() {
<<<<<<< HEAD
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
=======
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
=======
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
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c
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
<<<<<<< HEAD
    }


=======
<<<<<<< HEAD
    }


=======
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
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c

}