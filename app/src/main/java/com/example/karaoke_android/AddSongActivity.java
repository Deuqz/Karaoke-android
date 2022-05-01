package com.example.karaoke_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import database.DataBase;
import database.FilesDatabase;
import database.User;

public class AddSongActivity extends AppCompatActivity {

    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        database = new FilesDatabase(getApplicationContext());
        User user = getIntent().getParcelableExtra("User");
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
            intent.putExtra("User", (Parcelable) user);
            startActivity(intent);
        });
    }
}
