package com.example.myfirstapplication;

import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class List {

    /**
     * Attribute der Klasse List
     */
    ArrayList<String> products = new ArrayList<String>();

    /**
     * Default-Konstruktor der Klasse List
     */
    public List() {
    }

    /**
     * Abstrakte Methode addProduct() zum Hinzufuegen eines Produkts zu der Liste mit Hintergrundcheck,
     * ob Produkt bereits auf einer anderen (angegebenen) Liste vorhanden ist.
     *
     * @param product - Produkt welches hinzugefuegt werden soll
     * @param list - Liste, auf die Produkt hinzugefuegt werden soll
     * @param checkList - Liste, auf der ueberprueft werden soll, ob Produkt bereits vorhanden ist
     */
    public abstract void addProduct(String product, List list, List checkList);

    /**
     * Methode switchList() zum Listenwechsel eines Produkts.
     *
     * @param selected - Produkt, welches auf eine andere Liste geschoben werden soll
     * @param from - Liste, von der Produkt geloescht werden soll
     * @param to - Liste, auf die Produkt verschoben werden soll
     */
    public void switchList(String selected, List from, List to) {
        //Aktion noch hinzufuegen
    }

    /**
     * Methode deleteProduct() loescht ein Produkt von einer Liste.
     *
     * @param selected - Produkt, welches geloescht werden soll
     * @param list - Liste, von der Produkt geloescht werden soll
     */
    public void deleteProducts(String selected, List list) {


    }

    /**
     * Getter
     */
    public ArrayList<String> getProducts() {
        return products;
    }
}
