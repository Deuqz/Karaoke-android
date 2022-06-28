package com.example.karaoke_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import database.DataBase;
import database.ReadyDatabase;

public class LoginActivity extends AppCompatActivity {

    private DataBase database;
    private static final String LOG_TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new ReadyDatabase();
    }

    @SuppressLint("SetTextI18n")
    public void okPushed(View view) {
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        TextView textView = findViewById(R.id.errorMessage);
        if (email.isEmpty() || password.isEmpty()) {
            textView.setText("Please, fill all lines");
            textView.setVisibility(View.VISIBLE);
            return;
        }
        if (!database.containsUser(email)) {
            textView.setText("Incorrect email");
            textView.setVisibility(View.VISIBLE);
            return;
        }
        if (!database.containsPassword(email, password)) {
            textView.setText("Incorrect password");
            textView.setVisibility(View.VISIBLE);
            return;
        }
        textView.setVisibility(View.INVISIBLE);
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(LOG_TAG, "signIntWithEmail:success");
                        } else {
                            Log.w(LOG_TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                            startActivity(intent);
                        }
                    }
                });
        Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
        intent.putExtra("User", (Parcelable) database.getUser(email));
        startActivity(intent);
    }
}
