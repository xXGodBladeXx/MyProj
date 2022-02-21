package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Driving_Activity extends AppCompatActivity {
    public static final String BROADCAST = "PACKAGE_NAME.android.action.broadcast";
    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving);


        Intent notifyIntent = new Intent(this,MessageReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);
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