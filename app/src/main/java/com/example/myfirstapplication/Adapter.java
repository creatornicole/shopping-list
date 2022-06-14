package com.example.myfirstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb
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
    public abstract View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent);

    public void delete(ArrayList<String> list, String current) {
        //remove element
        int index = list.indexOf(current);
        list.remove(index);
    }
<<<<<<< HEAD

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
=======

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<String> getStringList() {
        return stringList;
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb
    }

}
