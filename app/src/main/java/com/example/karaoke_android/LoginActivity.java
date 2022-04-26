package com.example.karaoke_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import database.DataBase;
import database.FilesDatabase;
import database.SimpleDatabase;

public class LoginActivity extends AppCompatActivity {

    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new FilesDatabase(getApplicationContext());
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
        ;
        textView.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
        intent.putExtra("User", (Parcelable) database.getUser(email));
        startActivity(intent);
    }
}
