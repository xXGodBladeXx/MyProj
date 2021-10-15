package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
        private EditText password;
        private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
    }

    public void signup(View view) {
        Intent intent = new Intent(this, Driving.class);
        startActivity(intent);
    }
}