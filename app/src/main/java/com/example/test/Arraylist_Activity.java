package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Arraylist_Activity extends AppCompatActivity {
    private ListView mylistview;
    private CustomAdapter myadapter;
    private ArrayList<Massage> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraylist);
        list.add(new Massage("hey",R.drawable.ic_launcher_foreground,true,50));

        mylistview = findViewById(R.id.list_item);
        myadapter = new CustomAdapter(this,R.layout.massages_row,list);
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "item:"+list.get(i),Toast.LENGTH_LONG).show();
            }
        });
    }
}