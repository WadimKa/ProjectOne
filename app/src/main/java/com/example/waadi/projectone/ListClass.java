package com.example.waadi.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by waadi on 29.11.2016.
 */

public class ListClass extends AppCompatActivity {
    String [] items, names ;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        listView = (ListView) findViewById(R.id.ListViewMain);

        items = getResources().getStringArray(R.array.listOfLinks);
        names = getResources().getStringArray(R.array.listOfNames);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.list, names);
        listView.setAdapter(stringArrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("path", items[i]);
                startActivity(intent);

               // Toast.makeText(getApplicationContext(), items[i], Toast.LENGTH_LONG).show();
            }
        });
    }
}