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
    private static ArrayList<String> stringList;

    /**
     * Konstruktor des Adapters
     *
     * @param context
     * @param list
     */
    public Adapter(@NonNull Context context, @LayoutRes ArrayList<String> list) {
        super(context, 0 , list);
        mContext = context;
        stringList = list;
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

    public Context getmContext() {
        return mContext;
    }

    public ArrayList<String> getStringList() {
        return stringList;
    }

}
