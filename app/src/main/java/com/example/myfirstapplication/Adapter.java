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
<<<<<<< HEAD
    private int mRessource;
    private DataBaseHelper mDbHelper;
=======
<<<<<<< HEAD
    private int mRessource;
    private DataBaseHelper mDbHelper;
=======
<<<<<<< HEAD
    private int mRessource;
    private DataBaseHelper mDbHelper;
=======
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c
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
<<<<<<< HEAD
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
<<<<<<< HEAD

                Integer position = new Integer(v.getTag().toString()); //Position aus Tag erhalten
                String product = list.get(position); //Produkt aus ArrayList erhalten

                dbHelper.deleteOne(product);
                notifyDataSetChanged();
=======

                Integer position = new Integer(v.getTag().toString()); //Position aus Tag erhalten
                String product = list.get(position); //Produkt aus ArrayList erhalten

                dbHelper.deleteOne(product);
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c
                ArrayList<String> newList = dbHelper.getAllAsList();
                list.clear();
                for(String s: newList) {
                    list.add(s);
<<<<<<< HEAD
=======
                    notifyDataSetChanged();
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c
                }
            }
        });
        return convertView;
    }
<<<<<<< HEAD
=======
=======
    public abstract View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent);

    public void delete(ArrayList<String> list, String current) {
        //remove element
        int index = list.indexOf(current);
        list.remove(index);
    }
<<<<<<< HEAD
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c

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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<String> getStringList() {
        return stringList;
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
>>>>>>> 54c418ccf59357d6b9849b18ebd6acdf64fc8f3c
    }

}
