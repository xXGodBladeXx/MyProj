package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnLongClickListener , DialogInterface.OnClickListener {
    private Button loginc;
    private EditText editTextTextPersonName;
    private EditText editTextTextPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        loginc = findViewById(R.id.buttonLogin);
        loginc.setOnLongClickListener(this);
        SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
        String email = sp.getString("email","");
        String password = sp.getString("password","");
        if(!email.equals("") && !password.equals("")){
            editTextTextPersonName.setText(email);
            editTextTextPersonName.setText(password);

        }

    }

    public void login(View view) {
        Intent intent = new Intent(  this, Driving.class);
        intent.putExtra("name",editTextTextPersonName.getText().toString());
        String pass = editTextTextPassword.getText().toString();
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
      //  if(pass.length()>=8 && pass.contains(upperCaseChars) && pass.contains(numbers) && pass.contains(lowerCaseChars)) {
            if (editTextTextPersonName.getText().toString().contains("@") && editTextTextPersonName.getText().toString().contains(".")) {
                SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email",editTextTextPersonName.getText().toString());
                editor.putString("password",editTextTextPassword.getText().toString());
                editor.commit();
                intent.putExtra("name",editTextTextPersonName.getText().toString());
                startActivity(intent);
            }
 //       }
    }
    public void signup(View view){
        Intent intent = new Intent(  this, SignUp.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view) {
        editTextTextPersonName.setText("");
        editTextTextPersonName.setText("");
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsbox:
                Toast.makeText(Login.this, "Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.exitbox:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes",this);
        builder.setNegativeButton("No", this);
        AlertDialog dialog =builder.create();
        dialog.show();
    }
    public void onClick(DialogInterface dialog, int which){
        if(which==dialog.BUTTON_POSITIVE){
            super.onBackPressed();
            dialog.cancel();
        }
        if(which==dialog.BUTTON_NEGATIVE){
            dialog.cancel();
        }
    }
}