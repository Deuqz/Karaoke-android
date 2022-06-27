package com.example.karaoke_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import database.DataBase;
import database.ReadyDatabase;
import database.User;

public class ProfileActivity extends AppCompatActivity {

    DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        User user_main = getIntent().getParcelableExtra("User");
        User user = getIntent().getParcelableExtra("User2");
        database = new ReadyDatabase();
        TextView firstName = findViewById(R.id.firstName);
        firstName.setText(user.getFirstName());
        TextView secondName = findViewById(R.id.secondName);
        secondName.setText(user.getSecondName());
        TextView email = findViewById(R.id.email);
        email.setText(user.getEmail());
        TrackAdaptor trackAdapter = new TrackAdaptor(this, user.getTrackList());
        trackAdapter.setVisibleSwitch(false);
        Log.e("ProfileFragment", String.valueOf((new ReadyDatabase()).getUser(user.getEmail()).getTrackList().size()));
        Log.e("ProfileFragment", String.valueOf(user.getTrackList().size()));
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(trackAdapter);
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent(this, PlayMusicActivity.class);
            intent.putExtra("Track", (Parcelable) user.getTrackList().get(i));
            intent.putExtra("User", (Parcelable) user_main);
            intent.putExtra("User2", (Parcelable) user);
            intent.putExtra("CAME_FROM", 1);
            startActivity(intent);
        });
        Button backButton = findViewById(R.id.button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
            intent.putExtra("User", (Parcelable) user_main);
            startActivity(intent);
        });
    }
}
