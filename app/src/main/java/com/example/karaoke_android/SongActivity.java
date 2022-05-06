package com.example.karaoke_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

import database.Track;
import database.User;
import voice.TrackPlayer;
import voice.TrackPlayerSimple;
import voice.TrackWorker;
import voice.TrackWorkerSimple;
import voice.VoiceRecorder;
import voice.VoiceRecorderSimple;

// TODO THERE IS WORK FOR DENIS!!!!

public class SongActivity extends AppCompatActivity {
    private TrackPlayer trackPlayer;
    private VoiceRecorder voiceRecorder;
    private TrackWorker trackWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        TextView textView = findViewById(R.id.textView);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        User user = getIntent().getParcelableExtra("User");
        Track track = getIntent().getParcelableExtra("Track");
        textView.setText(track.getName());
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
            intent.putExtra("User", (Parcelable) user);
            startActivity(intent);
        });
        voiceRecorder = new VoiceRecorderSimple(getApplicationContext(), user);
        trackPlayer = new TrackPlayerSimple(getApplicationContext());
        trackWorker = new TrackWorkerSimple(getApplicationContext(), user);
    }

    public void playTrackPushed(View view) {
        trackWorker.start();
    }

    public void pauseTrackPushed(View view) {
        trackWorker.stop();
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
        voiceRecorder.startRecording();
    }

    public void stopRecordPushed(View view) {
        voiceRecorder.stopRecording();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        trackPlayer.stop();
        trackPlayer.close();
        trackWorker.close();
    }
}
