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

/**
 * Adapter for ListView elements of storagelist.
 *
 * @author Nicole Gottschall
 * @since 2022-06-01
 */

public class StorageAdapter extends Adapter {

    /**
     * Constructor to create Instance of Adapter for storagelist.
     *
     * @param context
     * @param list
     * @param dbHelper
     */
    public StorageAdapter(@NonNull Context context, ArrayList<String> list, DataBaseHelper dbHelper, DataBaseHelper dbHelperExtern) {
        super(context, list, dbHelper, dbHelperExtern);
    }
}