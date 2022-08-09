package com.example.myfirstapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Handles all Database-Operations
 *
 * @author Nicole Gottschall
 * @since 2022-06-14
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static String tableTitle; //tableTitle depends on given name of database
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_PRODUCT = "PRODUCT";


    /**
     * Constructor to create instance of DataBaseHelper
     *
     * @param context Context
     * @param name Name of Database
     */
    public DataBaseHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
        if(name.equals("shopping.db")) {
            tableTitle = "SHOPPING_TABLE";
        } else if(name.equals("storage-db")) {
            tableTitle = "STORAGE_TABLE";
        }
    }

    /**
     * Is called when DataBaseHelper is created for the first time.
     *
     * @param db Database that is going to be created
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + tableTitle
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT + " TEXT)";
        db.execSQL(createTableStatement);
    }

    /**
     * Upgrade from old database version to new database version.
     *
     * @param db Database
     * @param oldVersion old Version of Database
     * @param newVersion new Version of Database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Add product to database.
     *
     * @param product Product that is going to be added
     * @return true/false if add-function was executed successful
     */
    public boolean addOne(String product) {
        SQLiteDatabase db = this.getWritableDatabase(); //to work with data in database
        ContentValues cv = new ContentValues(); //stores data in pairs
        cv.put(COLUMN_PRODUCT, product);
        return db.insert(tableTitle, null, cv) > -1;
    }

    /**
     * Delete product from database.
     *
     * @param product Product that is going to be deleted
     * @return true/false if delete-function was executed successful
     */
    public boolean deleteOne(String product) {
        SQLiteDatabase db = this.getWritableDatabase(); //um mit Daten in Datenbank zu arbeiten
        return db.delete(tableTitle, COLUMN_PRODUCT + "=?", new String[]{product}) > 0;
    }

    /**
     * Get database entries as ArrayList.
     *
     * @return ArrayList with database entries
     */
    @SuppressLint("Range")
    public ArrayList<String> getAllAsList() {
        //create empty list
        ArrayList<String> returnList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase(); //um Daten aus Datenbank zu lesen
        Cursor cursor = db.query(tableTitle, null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            returnList.add(new String(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT))));
        }
        cursor.close();
        return returnList;
    }

    /**
     * Check if product already exists in database.
     *
     * @param product Product if it already exists in database
     * @return true/false if Product already exists in database
     */
    public boolean existsInDB(String product) {
        boolean rv = false;
        SQLiteDatabase db = this.getWritableDatabase(); //um mit Daten in Datenbank zu arbeiten
        Cursor cursor = db.query(tableTitle, null, COLUMN_PRODUCT + "=?", new String[]{product},null,null,null);
        if(cursor.moveToFirst()) {
            rv = true;
        }
        cursor.close();
        return rv;
    }
}