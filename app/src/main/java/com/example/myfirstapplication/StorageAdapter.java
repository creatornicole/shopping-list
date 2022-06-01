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

import com.example.myfirstapplication.MainActivity;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.StorageActivity;

import java.util.ArrayList;

public class StorageAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> stringList;

    /**
     * Konstruktor des Adapters
     *
     * @param context
     * @param list
     */
    public StorageAdapter(@NonNull Context context, @LayoutRes ArrayList<String> list) {
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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
                //remove element
                int index = list.indexOf(current);
                list.remove(index);
                //update ListView
                StorageActivity.getListView().invalidateViews();
            }

        });
        return listItem;
    }
}

