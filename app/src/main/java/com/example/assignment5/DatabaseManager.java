package com.example.assignment5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {


    private static final String TABLE_NAME = "listTable", ID = "id", NAME = "name";

    public DatabaseManager(Context context) {
        super(context, TABLE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " CHAR(25))";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<GroceryItem> selectAll() {
        String selectAll = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase database = getWritableDatabase();

        Cursor cursor = database.rawQuery(selectAll, null);

        ArrayList<GroceryItem> groceryItems= new ArrayList<>();

        //Loop that transfers info from cursor to arraylist
        while (cursor.moveToNext()) {
            GroceryItem currentItem = new GroceryItem(Integer.parseInt(cursor.getString(0)),
                                                                        cursor.getString(1));
            //Put candy object into arrayList
            groceryItems.add(currentItem);
        }

        database.close();

        return groceryItems;
    }


    public void insert(GroceryItem item) {
        SQLiteDatabase database = getWritableDatabase();

        String sqlInsert = "INSERT INTO " + TABLE_NAME + " VALUES(null,  '" + item.getName() + "')";

        database.execSQL(sqlInsert);

        database.close();

    }

    public void deleteById(int id) {

        String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;

        SQLiteDatabase database = getWritableDatabase();

        database.execSQL(sqlDelete);

        database.close();

    }

    public void clearDatabase() {
        String sqlClear = "DELETE FROM " + TABLE_NAME;

        SQLiteDatabase database = getWritableDatabase();

        database.execSQL(sqlClear);

        database.close();
    }


}
