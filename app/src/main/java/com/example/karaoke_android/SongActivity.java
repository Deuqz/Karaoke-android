package com.example.karaoke_android;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import database.Track;
import database.User;
import voice.TrackPlayer;
import voice.TrackPlayerSimple;
import voice.TrackWorker;
import voice.TrackWorkerSimple;
import voice.TrackWorkerSmart;
import voice.VoiceRecorderSimple;

import com.example.karaoke_android.LoginActivity;


public class SongActivity extends AppCompatActivity {
    private TrackPlayer trackPlayer;
    private VoiceRecorderSimple voiceRecorder;
    private TrackWorker trackWorker;
    private User user;
    private Track track;
    private final String LOG_TAG = "SongActivity";

    private void setSongText(String text) {
        TextView songTextView = findViewById(R.id.processView);
        songTextView.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        TextView textView = findViewById(R.id.processView);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        user = getIntent().getParcelableExtra("User");
        track = getIntent().getParcelableExtra("Track");
        textView.setText(track.getName());
        setSongText("There will be the text of the song))");
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
            intent.putExtra("User", (Parcelable) user);
            startActivity(intent);
        });
        LoginActivity act;
        voiceRecorder = new VoiceRecorderSimple(getApplicationContext(), user);
        trackPlayer = new TrackPlayerSimple(getApplicationContext());
        TextView textViewWithSongText = findViewById(R.id.textView2);
        trackWorker = new TrackWorkerSmart(getApplicationContext(), user, textViewWithSongText, textView);
    }

    private boolean isHeadphonesPlugged(){
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        AudioDeviceInfo[] audioDevices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);
        for(AudioDeviceInfo deviceInfo : audioDevices){
            if(deviceInfo.getType() == AudioDeviceInfo.TYPE_WIRED_HEADPHONES
                    || deviceInfo.getType() == AudioDeviceInfo.TYPE_WIRED_HEADSET
                    || deviceInfo.getType() == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP
                    || deviceInfo.getType() == AudioDeviceInfo.TYPE_BLUETOOTH_SCO){
                return true;
            }
        }
        return false;
    }

    public void playTrackPushed(View view) {
//        Log.d(LOG_TAG, String.valueOf(isHeadphonesPlugged()));
        if (isHeadphonesPlugged()) {
//            Log.e("Song Activity", String.valueOf(R.raw.track2text));
            Track track = new Track("track2", "somebody", "xxx.onion", "track2.mp3");
            track.setTextId("track2text.txt");
            Log.d("PlayTrackPushed", "Try play track");
            trackWorker.start(track);
        } else {
            Toast.makeText(this, "Please, connect headphones", Toast.LENGTH_SHORT).show();
        }
    }

    public void pauseTrackPushed(View view) {
        trackWorker.pause();
    }

    public void stopTrackPushed(View view) {
        trackWorker.stop();
    }

    // Requesting permission to RECORD_AUDIO
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = { Manifest.permission.RECORD_AUDIO };

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
