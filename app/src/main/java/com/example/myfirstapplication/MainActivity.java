package com.example.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
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
    private ImageView addBtn;
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

        customizeToolbar();
        assignVariables();
        registerClick();
        showAllProducts(dbHelper);
    }

    /**
     * Assign needed graphical elements to variables
     */
    private void assignVariables() {
        listView = (ListView) findViewById(R.id.lv);
        addBtn = (ImageView) findViewById(R.id.addBtn);
        switchBtn = (Button) findViewById(R.id.switchBtnToolbar);
        switchBtn.setText("Zum Lager >");
        tv = (EditText) findViewById(R.id.tv);
    }

    /**
     * Customizes Toolbar
     */
    private void customizeToolbar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getWindow().setStatusBarColor(getResources().getColor(R.color.darkTuerkis));
    }

    /**
     * Show all current stored products in database in ListView.
     *
     * @param dbHelper
     */
    private void showAllProducts(DataBaseHelper dbHelper) {
        ArrayList<String> list = dbHelper.getAllAsList();
        Collections.reverse(list);
        Adapter adapter = new ShoppingAdapter(this, list, dbHelper, dbHelperExtern);
        listView.setAdapter(adapter);
    }

    /**
     * Add OnClickListeners to the buttons.
     */
    private void registerClick() {
        //implementation of add product function
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = tv.getText().toString();

                if(Pattern.matches("s*", product)) { //ouputs toast if user did not add any title
                    Toast.makeText(MainActivity.this, "Produktname fehlt.", Toast.LENGTH_SHORT).show();

                } else if(dbHelper.existsInDB(product)) { //outputs toast if user entered product that already exists in the list
                    Toast.makeText(MainActivity.this, "Produkt befindet sich bereits auf dieser Liste.", Toast.LENGTH_LONG).show();

                } else if(dbHelperExtern.existsInDB(product)) {  //create dialog, ask if user wants to add product even though it is already stored
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Das Produkt befindet sich bereits auf der anderen Liste. Soll dies dennoch hinzugefügt werden?");
                    builder.setTitle("Produkt hinzufügen?");

                    builder.setCancelable(false); //the dialog will still show even if the user clicks on the outside of the box

                    //set yes button
                    builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelper.addOne(product);
                            showAllProducts(dbHelper);
                        }
                    });
                    //set no button
                    builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
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