package com.example.myfirstapplication;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myfirstapplication.MainActivity;
import com.example.myfirstapplication.R;

import java.util.ArrayList;

/**
 * Adapter for ListView elements of shoppinglist.
 *
 * @author Nicole Gottschall
 * @since 2022-06-01
 */

public class ShoppingAdapter extends Adapter {

    /**
     * Constructor to create Instance of Adapter for shoppinglist.
     *
     * @param context
     * @param list
     * @param dbHelper
     */
    public ShoppingAdapter(@NonNull Context context, ArrayList<String> list, DataBaseHelper dbHelper, DataBaseHelper dbHelperExtern) {
        super(context, list, dbHelper, dbHelperExtern);
    }
}