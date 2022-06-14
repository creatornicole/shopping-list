package com.example.myfirstapplication;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class List {

    private static ArrayList<String> products;

    public List(Context activity) {
        products = new ArrayList<String>();
    }

    public void addProduct(String product, Adapter adapter) {
        if(Pattern.matches("s*", product)) {
            //ignoriere leere Eingaben
        } else {
            products.add(product);
            Collections.reverse(products);
            adapter.notifyDataSetChanged();
        }
    }

    //add switchProduct()

    public ArrayList<String> getProducts() {
        return products;
    }


}
