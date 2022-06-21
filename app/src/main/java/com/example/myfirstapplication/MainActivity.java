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

/**
 * Activity of Shoppinglist.
 *
 * @author Nicole Gottschall
 * @since 2022-05-25
 */

public class MainActivity extends AppCompatActivity {

    private static ListView listView;
    private ImageButton addBtn;
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
        setContentView(R.layout.activity_main);

        //create databases
        dbHelper = new DataBaseHelper(MainActivity.this, "shopping.db");
        dbHelperExtern = new DataBaseHelper(MainActivity.this, "storage.db");

        assignVariables();
        registerClick();
        showAllProducts(dbHelper);
    }

    /**
     * Assign needed graphical elements to variables
     */
    public void assignVariables() {
        listView = (ListView) findViewById(R.id.lv);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        switchBtn = (Button) findViewById(R.id.switchBtn);
        tv = (EditText) findViewById(R.id.tv);
    }

    /**
     * Show all current stored products in database in ListView.
     *
     * @param dbHelper
     */
    public void showAllProducts(DataBaseHelper dbHelper) {
        ArrayList<String> list = dbHelper.getAllAsList();
        Collections.reverse(list);
        Adapter adapter = new ShoppingAdapter(this, list, dbHelper, dbHelperExtern);
        listView.setAdapter(adapter);
    }

    /**
     * Add OnClickListeners to the buttons.
     */
    public void registerClick() {
        //implementation of add product function
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = tv.getText().toString();

                if(Pattern.matches("s*", product)) { //ouputs toast if user did not add any title
                    Toast.makeText(MainActivity.this, "Title is missing", Toast.LENGTH_SHORT).show();

                } else if(dbHelper.existsInDB(product)) { //outputs toast if user entered product that already exists in the list
                    Toast.makeText(MainActivity.this, "Already added Product to list", Toast.LENGTH_LONG).show();

                } else if(dbHelperExtern.existsInDB(product)) {  //create dialog, ask if user wants to add product even though it is already stored
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("The product you want to add is already stored. Do you still want to add it?");
                    builder.setTitle("Add Product?");

                    builder.setCancelable(false); //the dialog will still show even if the user clicks on the outside of the box

                    //set yes button
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelper.addOne(product);
                            showAllProducts(dbHelper);
                        }
                    });
                    //set no button
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
                //Empty TextView
                tv.setText("");
            }
        });

        //button to switch to other activity
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StorageActivity.class);
                startActivity(i);
            }
        });
    }
}