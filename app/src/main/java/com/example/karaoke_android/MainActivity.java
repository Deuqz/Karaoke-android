package com.example.karaoke_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import database.Track;
import voice.TrackPlayer;
import voice.TrackPlayerSimple;

public class MainActivity extends AppCompatActivity {
    TrackPlayer trackPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trackPlayer = new TrackPlayerSimple(getApplicationContext());
    }

    // code below from Denis
    private boolean pausePushed = false;
    public void playTrackPushed(View view) {
        if (!pausePushed) {
            trackPlayer.setTrack(new Track("track1", "somebody", R.raw.track1));
        }
        trackPlayer.play();
    }

    public void pauseTrackPushed(View view) {
        trackPlayer.pause();
        pausePushed = true;
    }

    public void recordPushed(View view) {

    }

    public void stopRecordPushed(View view) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        trackPlayer.stop();
    }
}
