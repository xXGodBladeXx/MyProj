package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity implements View.OnLongClickListener {
    private static final String TAG = "FireBase";
    private Button loginc;
    private EditText editTextTextPersonName;
    private EditText editTextTextPassword;
    private FirebaseAuth mAuth;//auth = authentication אימות

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        loginc = findViewById(R.id.buttonLogin);
        loginc.setOnLongClickListener(this);
        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
        String email = sp.getString("email", "");
        String password = sp.getString("password", "");
        if (!email.equals("") && !password.equals("")) {
            editTextTextPersonName.setText(email);
        }

    }

    public void login(View view) {
            SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("email", editTextTextPersonName.getText().toString());
            editor.commit();
            loginfb(editTextTextPersonName.getText().toString(),editTextTextPassword.getText().toString());
    }

    public void signup(View view) {
        Intent intent = new Intent(this, SignUp_Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view) {
        editTextTextPersonName.setText("");
        editTextTextPersonName.setText("");
        return true;
    }

    public void loginfb(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent i = new Intent(Login_Activity.this, Driving_Activity.class);
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login_Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}