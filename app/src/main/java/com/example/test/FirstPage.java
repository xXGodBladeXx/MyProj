package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
    }
    public void login(View view){
        Intent intent = new Intent(  this, Login.class);
        startActivity(intent);
    }
    public void howitworks(View view){
        Intent intent = new Intent(  this, Speak.class);
        startActivity(intent);
    }
}