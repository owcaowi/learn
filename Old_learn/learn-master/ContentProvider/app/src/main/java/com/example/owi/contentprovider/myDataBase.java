package com.example.owi.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;


public class myDataBase extends SQLiteOpenHelper {


    public Context context;

    public myDataBase(Context context) {
        super(context, "database.db", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE dogs( id INTEGER PRIMARY KEY AUTOINCREMENT,  name TEXT, cartoon TEXT ); ");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public List<Dogs> getAllDogs(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor kursor = db.query("dogs", new String[]{"id", "name", "cartoon"}, null, null, null, null, null);

        List<Dogs> allDogsList = new LinkedList<Dogs>();

        while( kursor.moveToNext()  ){

            Dogs newDog = new Dogs(null,"","");
            newDog.setId(kursor.getInt(0));
            newDog.setName(kursor.getString(1));
            newDog.setCartoon(kursor.getString(2));

            allDogsList.add(newDog);
        }

        return allDogsList;
    }


    public void addDog(Dogs dogs) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", dogs.getId());
        values.put("name", dogs.getName());
        values.put("cartoon", dogs.getCartoon());
        db.insertOrThrow("dogs", null, values);
    }


}
