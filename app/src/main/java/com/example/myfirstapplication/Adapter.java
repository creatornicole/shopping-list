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
 * Adapter fuer ListView-Elementen
 *
 * @author Nicole Gottschall
 * @since 2022-05-31
 */

public abstract class Adapter extends ArrayAdapter<String> {

    private static Context mContext;
    private int mRessource;
    private DataBaseHelper mDbHelper;
    private DataBaseHelper mDbHelperExtern;
    private static ArrayList<String> stringList;

    /**
     * Konstruktor des Adapters
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

        //erhalte weitere benoetigte Elemente
        ArrayList<String> list = getStringList();
        DataBaseHelper dbHelper = getmDbHelper();

        //Loesch-Funktion
        ImageView delView = (ImageView) convertView.findViewById(R.id.delView);
        delView.setTag(position); //setzt Position in Liste als Tag
        delView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = new Integer(v.getTag().toString()); //Position aus Tag erhalten
                String product = list.get(position); //Produkt aus ArrayList erhalten

                deleteView(list, dbHelper, product);
            }
        });

        //Switch-Funktion
        ImageView switchView = (ImageView) convertView.findViewById(R.id.switchView);
        switchView.setTag(position); //setzt Position in Liste als Tag
        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Produkt von der einen Liste auf die andere schieben
                Integer position = new Integer(v.getTag().toString()); //Position aus Tag erhalten
                String product = list.get(position); //Produkt aus ArrayList erhalten

                //von aktueller Liste/ Datenbank loeschen
                deleteView(list, dbHelper, product);
                //zu anderen Liste/ Datenbank hinzufuegen
                if(mDbHelperExtern.existsInDB(product)) {
                    //Ausgabe Toast, wenn Produkt auf anderer Liste schon vorhanden ist
                    Toast.makeText(mContext, "Already on other list",Toast.LENGTH_LONG).show();
                } else {
                    mDbHelperExtern.addOne(product);
                }



            }
        });
        return convertView;
    }

    public void deleteView(ArrayList<String> list, DataBaseHelper dbHelper, String product) {
        dbHelper.deleteOne(product);
        notifyDataSetChanged();
        ArrayList<String> newList = dbHelper.getAllAsList();
        list.clear();
        for (String s : newList) {
            list.add(s);
        }
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