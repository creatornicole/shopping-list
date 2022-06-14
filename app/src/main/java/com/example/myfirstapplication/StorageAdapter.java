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
<<<<<<< HEAD
=======
<<<<<<< HEAD



    public StorageAdapter(@NonNull Context context, ArrayList<String> list, DataBaseHelper dbHelper) {
        super(context, list, dbHelper);
=======
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695

    /**
     * Konstruktor des Adapters
     *
     * @param context
     * @param list
     * @param dbHelper
     */
<<<<<<< HEAD
    public StorageAdapter(@NonNull Context context, ArrayList<String> list, DataBaseHelper dbHelper) {
        super(context, list, dbHelper);
=======
    public StorageAdapter(@NonNull Context context, @LayoutRes ArrayList<String> list) {
        super(context, list);
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb
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
<<<<<<< HEAD
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
=======
        Context mContext = getmContext();
        ArrayList<String> stringList = getStringList();

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        //get Elements of ListItem
        TextView text = (TextView) listItem.findViewById(R.id.text);
        ImageButton delBtn = (ImageButton) listItem.findViewById(R.id.delBtn);

        //get clicked Item
        String current = stringList.get(position);
        //get Element from ArrayList
        ArrayList<String> list = StorageActivity.getList();
        //show clicked Item in ListView
        text.setText(list.get(position));

        //OnButtonClickEvent to delete
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(list, current);
                //update ListView
                StorageActivity.getListView().invalidateViews();
            }
>>>>>>> 7cf137685bafd7eb5462c1e78289ac404e7d08eb

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
>>>>>>> 6b35162136b68a81f4155506e44d7e2bdbd98695
    }
}

