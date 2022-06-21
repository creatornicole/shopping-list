package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private EditText tv;
    private static Adapter adapter;
    private DataBaseHelper dbHelper;
    private DataBaseHelper dbHelperExtern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Erstellen der grafischen Oberflaeche
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DataBaseHelper(MainActivity.this, "shopping.db");
        dbHelperExtern = new DataBaseHelper(MainActivity.this, "storage.db");


        assignVariables();
        registerClick();
        showAllProducts(dbHelper);
    }

    /**
     * Weisst die benoetigten grafischen Elemente zu Variablen zu
     */
    public void assignVariables() {
        listView = (ListView) findViewById(R.id.lv);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        switchBtn = (Button) findViewById(R.id.switchBtn);
        tv = (EditText) findViewById(R.id.tv);
    }

    public void showAllProducts(DataBaseHelper dbHelper) {
        ArrayList<String> list = dbHelper.getAllAsList();
        Collections.reverse(list);
        Adapter adapter = new ShoppingAdapter(this, list, dbHelper, dbHelperExtern);
        listView.setAdapter(adapter);
    }

    public void registerClick() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = tv.getText().toString();
                if(Pattern.matches("s*", product)) {
                    Toast.makeText(MainActivity.this, "Title is missing", Toast.LENGTH_SHORT).show();
                } else if(dbHelper.existsInDB(product)) {
                    Toast.makeText(MainActivity.this, "Already added Product to list", Toast.LENGTH_LONG).show();
                } else if(dbHelperExtern.existsInDB(product)) {
                    //create dialog, ask if user wants to add product even though it is already stored
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("The product you want to add is already stored. Do you still want to add it?");
                    builder.setTitle("Add Product?");
                    //the dialog will remain show even if the user clicks on the outside of the box
                    builder.setCancelable(false);

                    //set yes button
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelper.addOne(product);
                            showAllProducts(dbHelper);
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    //create dialog
                    AlertDialog alertDialog = builder.create();

                    //show dialog
                    alertDialog.show();

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



}