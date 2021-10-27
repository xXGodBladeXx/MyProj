package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class Driving extends AppCompatActivity {
    public static final String BROADCAST = "PACKAGE_NAME.android.action.broadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);


    }
}