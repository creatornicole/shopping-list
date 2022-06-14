package com.example.myfirstapplication;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public abstract class ActivityParent extends AppCompatActivity {

    /**
     * Attributes
     */
    private static ListView listView;
    private ImageButton addBtn;
    private Button switchBtn;
    private EditText tv;
    private static Adapter adapter;
    private DataBaseHelper dbHelper;

    /**
     * Weisst die benoetigten grafischen Elemente zu Variablen zu
     */
    public void assignVariables() {
        listView = (ListView) findViewById(R.id.lv);
        addBtn = (ImageButton) findViewById(R.id.addBtn);
        switchBtn = (Button) findViewById(R.id.switchBtn);
        tv = (EditText) findViewById(R.id.tv);
    }

    /**
     * Registriert Button-Klicks
     */
    public abstract void registerClick();

    /**
     * Richtet neuen Adapter ein und fuegt alle Elemente aus Liste neu hinzu.
     *
     * @param dbHelper
     */
    public abstract void showAllProducts(DataBaseHelper dbHelper);

    public static ListView getListView() {
        return listView;
    }

    public static void setListView(ListView listView) {
        ActivityParent.listView = listView;
    }

    public ImageButton getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(ImageButton addBtn) {
        this.addBtn = addBtn;
    }

    public Button getSwitchBtn() {
        return switchBtn;
    }

    public void setSwitchBtn(Button switchBtn) {
        this.switchBtn = switchBtn;
    }

    public EditText getTv() {
        return tv;
    }

    public void setTv(EditText tv) {
        this.tv = tv;
    }

    public static Adapter getAdapter() {
        return adapter;
    }

    public static void setAdapter(Adapter adapter) {
        ActivityParent.adapter = adapter;
    }

    public DataBaseHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DataBaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
}
