package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class FirstPage_Activity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private Intent musicintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
    }
    public void login(View view){
        Intent intent = new Intent(  this, Login_Activity.class);
        startActivity(intent);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Camera:
                Intent intent = new Intent(  this, Camera_Activity.class);
                startActivity(intent);
                Toast.makeText(FirstPage_Activity.this, "Camera", Toast.LENGTH_LONG).show();
                break;
            case R.id.exitbox:
                finish();
                break;
            case R.id.Howitworks:
                Intent i = new Intent(  this, HowItWorks_Activity.class);
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//object which interact with the user
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", this);
        builder.setNegativeButton("No", this);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //the dialog interface is implemented in the top of the file that way he knows the on click is for the dialog
    public void onClick(DialogInterface dialog, int which) {
        if (which == dialog.BUTTON_POSITIVE) {//did the user press positive
            super.onBackPressed();
            dialog.cancel();
        }
        if (which == dialog.BUTTON_NEGATIVE) {//did the user press negative
            dialog.cancel();
        }
    }

    public void howitworks(View view){
        Intent intent = new Intent(  this, HowItWorks_Activity.class);
        startActivity(intent);

        musicintent = new Intent(this,MusicService.class);
        startService(musicintent);

    }

}