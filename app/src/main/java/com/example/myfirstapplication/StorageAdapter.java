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

import com.example.myfirstapplication.MainActivity;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.StorageActivity;

import java.util.ArrayList;

public class StorageAdapter extends Adapter {



    public StorageAdapter(@NonNull Context context, ArrayList<String> list, DataBaseHelper dbHelper) {
        super(context, list, dbHelper);
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
}

