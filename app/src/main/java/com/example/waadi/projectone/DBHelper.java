package com.example.waadi.projectone;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by waadi on 11.12.2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    String [] names, properties, url, img;

    public DBHelper(Context context, String [] names, String [] properties, String [] url) {
        super(context, "tableProjectOne", null, 1);
        this.names=names;
        this.properties=properties;
        this.url=url;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ContentValues cv = new ContentValues();
        sqLiteDatabase.execSQL("create table mytable (id integer primary key autoincrement, name text, prop text, url text)");

        for(int i = 0;i<names.length;i++){
            cv.put("name",names[i]);
            cv.put("prop",properties[i]);
            cv.put("url",url[i]);
            sqLiteDatabase.insert("mytable", null, cv);


        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
