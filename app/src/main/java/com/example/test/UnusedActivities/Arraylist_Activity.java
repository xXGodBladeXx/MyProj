package com.example.test.UnusedActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test.R;

import java.util.ArrayList;

public class Arraylist_Activity extends AppCompatActivity{

    //the object of the view - design
    private ListView myListView;
    //the object for the adaptor connection the data to the view
    private CustomAdapter myAdapter;
    //object containing the item to be displayed - Data
    private ArrayList<item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraylist);
        list = new ArrayList<>();

        list.add(new item("this is my first Item", R.drawable.back, true, 50));
        list.add(new item("this is my second Item", R.drawable.back, true, 50));
        list.add(new item("this is my third Item", R.drawable.back, false, 70));

        //reference to the list view so it can programed
        myListView = findViewById(R.id.listitem);
        // connect adapter with Data
        myAdapter = new CustomAdapter(this,R.layout.massages_row, list);
        //connect adapter with view
        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Item:" + list.get(i), Toast.LENGTH_LONG).show();
            }
        });
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

}