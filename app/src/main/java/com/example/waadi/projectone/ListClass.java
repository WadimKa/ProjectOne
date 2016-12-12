package com.example.waadi.projectone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by waadi on 29.11.2016.
 */

public class ListClass extends AppCompatActivity {
    String [] links, names, properties ;
    ListView listView;
    DBHelper dbHelper;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        listView = (ListView) findViewById(R.id.ListViewMain);

        links = getResources().getStringArray(R.array.listOfLinks);
        names = getResources().getStringArray(R.array.listOfNames);
        properties = getResources().getStringArray(R.array.Properties);


        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.list, names);
        listView.setAdapter(stringArrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("path", links[i]);
                startActivity(intent);


            }
        });
    }

    public void addNew(View view) {
        Toast.makeText(this, showDBList(""),Toast.LENGTH_LONG).show();
    }

    public String showDBList(String nameColumn){
        dbHelper = new DBHelper(this, names, properties, links);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("mytable", null, null, null, null, null, null);
        ArrayList<String> strings = new ArrayList<>();
        if(cursor.moveToFirst()){
            int colID = cursor.getColumnIndex("id");
            int colName = cursor.getColumnIndex("name");
            int colProp = cursor.getColumnIndex("prop");
            int colUrl = cursor.getColumnIndex("url");

            do{
                String buble = "--"+cursor.getString(colName)+"--"+cursor.getString(colProp)+"--"+cursor.getString(colUrl);
                strings.add(buble);
            }while(cursor.moveToNext());
        }else{
            cursor.close();
        }
        cursor.close();
        dbHelper.close();
        return strings.get(2);
    }

    public void putForSimpleAdapter(){
        
    }
}