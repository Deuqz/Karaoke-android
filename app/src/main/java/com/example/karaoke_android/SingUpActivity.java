package com.example.karaoke_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import database.DataBase;
import database.FilesDatabase;
import database.User;

public class SingUpActivity extends AppCompatActivity {

    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        database = new FilesDatabase(getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    public void okPushed(View view) {
        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String secondName = ((EditText) findViewById(R.id.secondName)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        TextView textView = findViewById(R.id.errorMessage);
        if (firstName.isEmpty() || secondName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            textView.setText("Please, fill all lines");
            textView.setVisibility(View.VISIBLE);
            return;
        }
        if (database.containsUser(email)) {
            textView.setText("This user already exists");
            textView.setVisibility(View.VISIBLE);
            return;
        }
        textView.setVisibility(View.INVISIBLE);
        User newUser = new User(firstName, secondName, email, password);
        try {
            database.add(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
        intent.putExtra("User", (Parcelable) newUser);
        startActivity(intent);
    }
}
