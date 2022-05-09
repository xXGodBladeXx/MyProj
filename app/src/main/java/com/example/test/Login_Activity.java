package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity implements View.OnLongClickListener ,DialogInterface.OnClickListener {
    private Intent musicintent;
    private Button loginc;
    private EditText Email;
    private EditText Password;
    private FirebaseAuth mAuth;//auth = authentication אימות

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();//a method that connects the firebase console with the project
        Email = findViewById(R.id.editTextTextPersonName);
        Password = findViewById(R.id.editTextTextPassword);
        loginc = findViewById(R.id.buttonLogin);

        //sets the OnClickListener on the wanted button
        loginc.setOnLongClickListener(this);

        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);//creates a local file which saves the sp in it
        String email = sp.getString("email", "");//the email in the local file
        String password = sp.getString("password", "");//the password in the local file
        if (!email.equals("") && !password.equals("")) {//checks if the edit text is empty and if it is it
            // presets the email with the email that it saved from before
            Email.setText(email);
            Password.setText(password);
        }
        musicintent = new Intent(this,MusicService.class);
        //startService(musicintent);
        loginc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginfb(Email.getText().toString(), Password.getText().toString());
            }
        });

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Camera:
                Intent intent = new Intent(  this, Camera_Activity.class);
                startActivity(intent);
                Toast.makeText(Login_Activity.this, "Camera", Toast.LENGTH_LONG).show();
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

    public void loginSP() {
        //saving email of user in a local file (settings) for future use
        //create file if not found
            SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
            //open editor for editing
            SharedPreferences.Editor editor = sp.edit();
            //write the wanted settings
            editor.putString("email", Email.getText().toString());
            editor.putString("password", Password.getText().toString());
            //saves and closes file
            editor.commit();


     //       loginfb(editTextTextPersonName.getText().toString(),editTextTextPassword.getText().toString());
    }

    public void signupgo(View view) {
        Intent intent = new Intent(this, SignUp_Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view) {
        Email.setText("");
        Password.setText("");
        return true;
    }

    public void loginfb(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)//a method that is premade in the fire base that checks the
                // email and password of the user and signs him in accordingly
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    //OnCompleteListener waits for the firebase
                    // Signin task to complete and returns
                    // a task which we check if it was made successfully
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FireBase", "signInWithEmail:success");
                            Intent i = new Intent(Login_Activity.this, Driving_Activity.class);
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginSP();
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FireBase", "signInWithEmail:failure", task.getException());//a description of the error for the task that wasn't successful
                            Toast.makeText(Login_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}