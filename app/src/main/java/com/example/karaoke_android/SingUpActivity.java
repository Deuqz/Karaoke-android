package com.example.karaoke_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import database.DataBase;
import database.SimpleDatabase;
import database.User;

public class SingUpActivity extends AppCompatActivity {

    DataBase database = new SimpleDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
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
        database.add(new User(firstName, secondName, email, password));
        Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
        startActivity(intent);
    }
}
