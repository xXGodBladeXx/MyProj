package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class Driving_Activity extends AppCompatActivity {
    public static final String BROADCAST = "PACKAGE_NAME.android.action.broadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);


    }
    public void Arraylistgo(View view) {
        Intent intent = new Intent(this, Arraylist_Activity.class);
        startActivity(intent);
    }
}