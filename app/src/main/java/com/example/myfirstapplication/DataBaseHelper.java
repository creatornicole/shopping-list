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

    //need two different database for two lists
    public static String tableTitle;
    public static final String STORAGE_TABLE = "STORAGE_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_PRODUCT = "PRODUCT";


    public DataBaseHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
        if(name.equals("shopping.db")) {
            tableTitle = "SHOPPING_TABLE";
        } else if(name.equals("storage-db")) {
            tableTitle = "STORAGE_TABLE";
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + tableTitle
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(String product) {
        SQLiteDatabase db = this.getWritableDatabase(); //um mit Daten in Datenbank zu arbeiten
        ContentValues cv = new ContentValues(); //speichert Daten in Paaren
        cv.put(COLUMN_PRODUCT, product);
        return db.insert(tableTitle, null, cv) > -1;
    }

    public boolean deleteOne(String product) {
        SQLiteDatabase db = this.getWritableDatabase(); //um mit Daten in Datenbank zu arbeiten
        return db.delete(tableTitle, COLUMN_PRODUCT + "=?", new String[]{product}) > 0;
    }

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
