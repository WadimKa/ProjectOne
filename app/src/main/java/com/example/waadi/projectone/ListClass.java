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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by waadi on 29.11.2016.
 */

public class ListClass extends AppCompatActivity {
    String ATTRIBUTE_NAME="name", ATTRIBUTE_PROP = "prop", ATTRIBUTE_URL = "url";
    String [] links, names, properties ;
    ArrayList<String> linksBD, namesBD, propertiesBD ;
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

        dbHelper = new DBHelper(this, names, properties, links);

        String [] from = {ATTRIBUTE_NAME, ATTRIBUTE_PROP};
        int [] to = {R.id.textViewName, R.id.textViewProp};

        SimpleAdapter stringArrayAdapter = new SimpleAdapter(this, putForSimpleAdapter(), R.layout.list_for_main_screen, from, to);
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
       // Toast.makeText(this, showDBList(""),Toast.LENGTH_LONG).show();
    }

    public ArrayList<String> showDBList(String nameColumn){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("mytable", null, null, null, null, null, null);
        ArrayList<String> strings = new ArrayList<>();
        if(cursor.moveToFirst()){
            int columnId = cursor.getColumnIndex(nameColumn);
            do{
                strings.add(cursor.getString(columnId));
            }while(cursor.moveToNext());
        }else{
            cursor.close();
        }
        cursor.close();
        dbHelper.close();
        return strings;
    }

    public ArrayList<Map<String,Object>> putForSimpleAdapter(){
        ArrayList<String> arrNames = showDBList("name");
        ArrayList<String> arrProperties = showDBList("prop");
        ArrayList<String> arrUrl = showDBList("url");
        ArrayList<Map<String,Object>> data = new ArrayList<>(arrNames.size());
        Map<String,Object> map;
        for(int i = 0;i<arrNames.size();i++){
            map = new HashMap<String,Object>();
            map.put(ATTRIBUTE_NAME, arrNames.get(i));
            map.put(ATTRIBUTE_PROP, arrProperties.get(i));
            map.put(ATTRIBUTE_URL, arrUrl.get(i));
            data.add(map);
        }
        return data;
    }
}