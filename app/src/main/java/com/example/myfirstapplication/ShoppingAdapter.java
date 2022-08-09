package com.example.myfirstapplication;

import android.content.Context;
import androidx.annotation.NonNull;
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
     * @param context Context
     * @param list ArrayList that is going to get connected to Adapter
     * @param dbHelper Database which stores values of ArrayList
     * @param dbHelperExtern Database which stores values of other existing ArrayList
     */
    public ShoppingAdapter(@NonNull Context context, ArrayList<String> list, DataBaseHelper dbHelper, DataBaseHelper dbHelperExtern) {
        super(context, list, dbHelper, dbHelperExtern);
    }
}