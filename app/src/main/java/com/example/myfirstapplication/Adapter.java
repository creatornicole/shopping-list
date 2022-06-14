package com.example.myfirstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Adapter fuer ListView-Elementen
 *
 * @author Nicole Gottschall
 * @since 2022-05-31
 */

public abstract class Adapter extends ArrayAdapter<String> {

    private static Context mContext;
    private int mRessource;
    private DataBaseHelper mDbHelper;
    private static ArrayList<String> stringList;

    /**
     * Konstruktor des Adapters
     *
     * @param context
     * @param list
     */
    public Adapter(@NonNull Context context, ArrayList<String> list, DataBaseHelper dbHelper) {
        super(context, R.layout.list_item, list);
        mContext = context;
        stringList = list;
        mDbHelper = dbHelper;
        mRessource = R.layout.list_item;
    }

    /**
     * getView() wird aufgerufen, wenn ListView-Element erstellt wird.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //geklicktes Element der ListView erhalten
        String product = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getmContext());
        convertView = inflater.inflate(getmRessource(), parent, false);

        //erhalte Textfeld
        TextView tv = (TextView) convertView.findViewById(R.id.product);

        //Info zu Textfeld hinzufuegen
        tv.setText(product);

        //Loesch-Funktion
        ImageView delView = (ImageView) convertView.findViewById(R.id.delView);
        delView.setTag(position); //setzt Position in Liste als Tag
        delView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = getStringList();
                DataBaseHelper dbHelper = getmDbHelper();

                Integer position = new Integer(v.getTag().toString()); //Position aus Tag erhalten
                String product = list.get(position); //Produkt aus ArrayList erhalten

                dbHelper.deleteOne(product);
                ArrayList<String> newList = dbHelper.getAllAsList();
                list.clear();
                for(String s: newList) {
                    list.add(s);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<String> getStringList() {
        return stringList;
    }

    public int getmRessource() {
        return mRessource;
    }

    public DataBaseHelper getmDbHelper() {
        return mDbHelper;
    }

}
