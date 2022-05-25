package com.example.myfirstapplication;

import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class ShoppingList extends List {




    /**
     * Default-Konstruktor der Klasse ShoppingList
     */
    public ShoppingList() {

    }

    /**
     * Die Methode addProduct() ist abstrakte Methode von abstrakter Klasse List.
     * Fuegt ein Produkt inklusive Hintergrundcheck zur Liste hinzu.
     *
     * @param product - Produkt welches hinzugefuegt werden soll
     * @param list - Liste, auf die Produkt hinzugefuegt werden soll - hier: Shoppinglist
     * @param checkList - Liste, auf der ueberprueft werden soll, ob Produkt bereits vorhanden ist - hier: Storage
     */
    @Override
    public void addProduct(String product, List list, List checkList) {
        //Zwischenlagerung der Elemente der uebergebenen Listen in Variablen
        ArrayList<String> products = list.getProducts();
        ArrayList<String> checkListProducts = checkList.getProducts();

        /*
        //Abwaegen, ob Eingabe getaetigt wurde
        if(Pattern.matches("s*", product)) {
            //ignoriert Aktion, wenn keine Eingabe getaetigt wurde
        } else {
            //Kontrolle, ob sich Produkt bereits auf eine der Listen befindet
            if(product.contains(product)) {
                //Produkt befindet sich bereits auf Shoppinglist
            } else if(checkListProducts.contains(product)) {
                //Produkt befindet sich bereits in Storage
            } else {
                //Produkt befindet sich auf keine der beiden Listen
                products.add(product);
            }
        }
        */
        if(Pattern.matches("s*", product)) {
            //ignoriert Aktion, wenn keine Eingabe getaetigt wurde

        } else {
            products.add(product);
            //kehre Anordnung der Liste um, sodass neu hinzugefuegtes Produkt ganz oben in der Liste erscheint
            Collections.reverse(products);
        }

    }



}
