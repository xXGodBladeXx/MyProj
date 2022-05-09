package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Driving_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);

    }
    public void Arraylistgo(View view) {
        Intent intent = new Intent(this, Arraylist_Activity.class);
        startActivity(intent);
    }
    public void FavArraylistgo(View view) {
        Intent intent = new Intent(this, FavArraylist_Activity.class);
        startActivity(intent);
    }
}