package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //the menu displayer
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate (displays and opens) a menu class on the activity that is currently open
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //what the buttons do when the menu opens
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {//switch selects a case on which the user selects
            case R.id.Camera:
                Intent intent = new Intent(  this, Camera_Activity.class);
                startActivity(intent);
                Toast.makeText(Menu_Activity.this, "Settings", Toast.LENGTH_LONG).show();
                break;//on the click it breaks the menu (closes it)
            case R.id.exitbox:
                break;
            case R.id.speak:
                Intent i = new Intent(  this, Speak_Activity.class);
                startActivity(i);

        }
            return super.onOptionsItemSelected(item);
    }
}