package com.example.owi.wallet.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {

    public Context context;

    public DataBase(Context context) {
        super(context, "database.db", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items( id INTEGER PRIMARY KEY AUTOINCREMENT,  name TEXT, price REAL, category_id INT, date INT ); ");
        db.execSQL("CREATE TABLE categories( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ); ");
        db.execSQL("CREATE TABLE limi( id INTEGER PRIMARY KEY AUTOINCREMENT, limi REAL, date TEXT ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            this.context.deleteDatabase("database.db");
            new DataBase(this.context);
        } else
            this.onUpgrade(db, oldVersion, newVersion);
    }

    /**
     *
     *  ITEMS TABLE
     *
     */

    public Items getItemById(Integer id) {

        Items item = new Items();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {id + ""};
        String[] columns = new String[]{"id", "name", "price", "category_id", "date"};
        Cursor kursor = db.query("items", columns, "id=?", args, null, null, null);

        while (kursor != null) {
            kursor.moveToFirst();
            item.setId(kursor.getInt(0));
            item.setName(kursor.getString(1));
            item.setPrice(kursor.getFloat(2));
            item.setCategory_id(kursor.getInt(3));
            item.setDate(kursor.getInt(4));
        }

        return item;
    }

    public List<Items> getAllItems() {

        List<Items> items = new LinkedList<Items>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{"id", "name", "price", "category_id", "date"};
        Cursor kursor = db.query("items", columns, null, null, null, null, null);

        while (kursor.moveToNext()) {
            Items tmp = new Items();
            tmp.setId(kursor.getInt(0));
            tmp.setName(kursor.getString(1));
            tmp.setPrice(kursor.getFloat(2));
            tmp.setCategory_id(kursor.getInt(3));
            tmp.setDate(kursor.getInt(4));
            items.add(tmp);
        }

        return items;

    }

    public List<Items> getAllItemsByDate(String date) {

        List<Items> items = new LinkedList<Items>();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {String.valueOf(date)};
        String[] columns = new String[]{"id", "name", "price", "category_id", "date"};
        Cursor kursor = db.query("items", columns, "date >= ?", args, null, null, null);

        while (kursor.moveToNext()) {
            Items tmp = new Items();
            tmp.setId(kursor.getInt(0));
            tmp.setName(kursor.getString(1));
            tmp.setPrice(kursor.getFloat(2));
            tmp.setCategory_id(kursor.getInt(3));
            tmp.setDate(kursor.getInt(4));
            items.add(tmp);
        }

        return items;

    }

    public void addItem(Items item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", item.getId());
        values.put("name", item.getName());
        values.put("price", item.getPrice());
        values.put("category_id", item.getCategory_id());
        values.put("date", item.getDate());
        db.insertOrThrow("items", null, values);
    }

    public void deleteItem(Integer id) {
        SQLiteDatabase db = getWritableDatabase();
        String args[] = {id + ""};
        db.delete("items", "id=?", args);
    }

    public void editItem(Items item) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        String args[] = {item.getId() + ""};
        values.put("name", item.getName());
        values.put("price", item.getPrice());
        values.put("category_id", item.getCategory_id());
        values.put("date", item.getDate());
        db.update("items", values, "id=?", args);
    }


    /**
     *
     *  CATEGORY TABLE
     *
     */

    public Categories getCategoryByName(String name) {

        Categories category = new Categories();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {name};
        String[] columns = new String[]{"id", "name"};
        Cursor kursor = db.query("categories", columns, "name LIKE ?", args, null, null, null);

        while (kursor.moveToNext()) {
            category.setId(kursor.getInt(0));
            category.setName(kursor.getString(1));
        }

        return category;
    }

    public Categories getCategoryById(Integer id) {

        Categories category = new Categories();
        SQLiteDatabase db = getReadableDatabase();
        String args[] = {id + ""};
        String[] columns = new String[]{"id", "name"};
        Cursor kursor = db.query("categories", columns, "id=?", args, null, null, null);

        while (kursor.moveToNext()) {
            category.setId(kursor.getInt(0));
            category.setName(kursor.getString(1));
        }

        return category;
    }

    public List<Categories> getAllCategories() {

        List<Categories> categories = new LinkedList<Categories>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{"id", "name"};
        Cursor kursor = db.query("categories", columns, null, null, null, null, null);

        while (kursor.moveToNext()) {
            Categories tmp = new Categories();
            tmp.setId(kursor.getInt(0));
            tmp.setName(kursor.getString(1));
            categories.add(tmp);
        }

        return categories;
    }

    public void addCategory(Categories category) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("name", category.getName());
        db.insertOrThrow("categories", null, values);
    }

    public void deleteAllCategories() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("categories", null, null);
    }


    /**
     *
     *  LIMIT TABLE
     *
     */

    public void addLimit(Limits limit) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("limi", limit.getLimit());
        values.put("date", limit.getDate());
        db.insertOrThrow("limi", null, values);


    }

    public Float getLimit() {

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{"id", "limi", "date"};
        Cursor kursor = db.query("limi", columns, null, null, null, null, "date DESC", "1");


        Float limi = new Float(0);
        while (kursor.moveToNext()) {
            limi = kursor.getFloat(1);
        }

        return limi;
    }

    public List<Limits> getAllLimits() {

        List<Limits> limits = new LinkedList<Limits>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = new String[]{"id", "limi", "date"};
        Cursor kursor = db.query("limi", columns, null, null, null, null, null);

        while (kursor.moveToNext()) {
            Limits tmp = new Limits();
            tmp.setId(kursor.getInt(0));
            tmp.setLimit(kursor.getFloat(1));
            tmp.setDate(kursor.getString(2));
            limits.add(tmp);
        }
        return limits;

    }

    public void deleteAllLimits() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("limi", null, null);
    }


}


