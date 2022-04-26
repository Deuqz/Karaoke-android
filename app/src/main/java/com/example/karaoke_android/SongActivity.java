package com.example.karaoke_android;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import database.Track;
import database.User;
import voice.TrackPlayer;
import voice.TrackPlayerSimple;
import voice.VoiceRecorder;
import voice.VoiceRecorderSimple;

// TODO THERE IS WORK FOR DENIS!!!!

public class SongActivity extends AppCompatActivity {
    TrackPlayer trackPlayer;
    VoiceRecorder voiceRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        TextView textView = findViewById(R.id.textView);
        textView.setText(getIntent().getStringExtra("trackName"));
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        trackPlayer = new TrackPlayerSimple(getApplicationContext());
        User user = getIntent().getParcelableExtra("User");
        voiceRecorder = new VoiceRecorderSimple(getApplicationContext(), user);
    }

    // code below from Denis
    private boolean pausePushed = false;

    public void playTrackPushed(View view) {
        if (!pausePushed) {
            trackPlayer.setTrack(new Track("track1", "somebody", "", R.raw.track1));
        }
        trackPlayer.play();
    }

    public void pauseTrackPushed(View view) {
        trackPlayer.pause();
        pausePushed = true;
    }

    // Requesting permission to RECORD_AUDIO
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        if (!permissionToRecordAccepted) {
            finish();
        }
    }

    public void recordPushed(View view) {
    }

    public void stopRecordPushed(View view) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        trackPlayer.stop();
        trackPlayer.close();
    }
}
