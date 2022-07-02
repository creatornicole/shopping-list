package com.example.myfirstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Adapter for ListView elements
 *
 * @author Nicole Gottschall
 * @since 2022-05-31
 */

public abstract class Adapter extends ArrayAdapter<String> {

    private Context mContext;
    private int mRessource;
    private DataBaseHelper mDbHelper;
    private DataBaseHelper mDbHelperExtern;
    private ArrayList<String> stringList;

    /**
     * Constructor to create Instance of Adapter
     *
     * @param context
     * @param list
     */
    public Adapter(@NonNull Context context, ArrayList<String> list, DataBaseHelper dbHelper, DataBaseHelper dbHelperExtern) {
        super(context, R.layout.list_item, list);
        mContext = context;
        stringList = list;
        mDbHelper = dbHelper;
        mDbHelperExtern = dbHelperExtern;
        mRessource = R.layout.list_item;
    }

    /**
     * Is called when new ListView element is created.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get clicked element of ListView
        String product = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getmContext());
        convertView = inflater.inflate(getmRessource(), parent, false);

        //get TextField
        TextView tv = (TextView) convertView.findViewById(R.id.product);

        //add product to TextView
        tv.setText(product);

        //get other needed elements
        ArrayList<String> list = getStringList();
        DataBaseHelper dbHelper = getmDbHelper();

        //implementation of delete function
        ImageView delView = (ImageView) convertView.findViewById(R.id.delView);
        delView.setTag(position); //sets position in list as tag
        delView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = new Integer(v.getTag().toString()); //get position set as tag
                String product = list.get(position); //get product from ArrayList

                deleteView(list, dbHelper, product);
            }
        });

        //implementation of product switch function
        ImageView switchView = (ImageView) convertView.findViewById(R.id.switchView);
        switchView.setTag(position); //sets position in list as tag
        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //transfer product from current list to the other list
                Integer position = new Integer(v.getTag().toString()); //get position set as tag
                String product = list.get(position); //get product from ArrayList

                //delete product from current list and related database
                deleteView(list, dbHelper, product);
                //add product to other list and related database
                if(mDbHelperExtern.existsInDB(product)) { //outputs Toast if product already exists on other list
                    Toast.makeText(mContext, "Befindet sich bereits auf der anderen Liste.",Toast.LENGTH_LONG).show();
                } else { //adds product to other list and related database if other list does not contain this product
                    mDbHelperExtern.addOne(product);
                }
            }
        });
        return convertView;
    }

    /**
     * Method to delete product from database and update ListView.
     *
     * @param list
     * @param dbHelper
     * @param product
     */
    private void deleteView(ArrayList<String> list, DataBaseHelper dbHelper, String product) {
        dbHelper.deleteOne(product);
        notifyDataSetChanged();
        ArrayList<String> newList = dbHelper.getAllAsList();
        list.clear();
        for (String s : newList) {
            list.add(s);
        }
    }

    /**
     * Getter - get given Context
     *
     * @return
     */
    public Context getmContext() {
        return mContext;
    }

    /**
     * Getter - get given list of products
     *
     * @return
     */
    public ArrayList<String> getStringList() {
        return stringList;
    }

    /**
     * Getter - get position of given ListView element
     *
     * @return
     */
    public int getmRessource() {
        return mRessource;
    }

    /**
     * Getter - get database of list
     *
     * @return
     */
    public DataBaseHelper getmDbHelper() {
        return mDbHelper;
    }

}