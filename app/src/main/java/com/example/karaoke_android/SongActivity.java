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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import database.Track;
import database.User;
import voice.TrackWorker;
import voice.TrackWorkerSmart;


public class SongActivity extends AppCompatActivity {
    private TrackWorker trackWorker;
    private User user;
    private Track track;

    private ImageView pausePlayButtom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        TextView processTextView = findViewById(R.id.processView);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        user = getIntent().getParcelableExtra("User");
        track = getIntent().getParcelableExtra("Track");
        processTextView.setText(track.getName());
        Button backButton = findViewById(R.id.song_back_button);
        backButton.setOnClickListener(v -> {
            trackWorker.close();
            Intent intent = new Intent(this, com.example.karaoke_android.MainActivity.class);
            Log.e("Song Activity", String.valueOf(user.getTrackList().size()));
            intent.putExtra("User", (Parcelable) user);
            startActivity(intent);
        });
        pausePlayButtom = findViewById(R.id.song_pause_play);
        trackWorker = new TrackWorkerSmart(this, user, findViewById(R.id.textView2), processTextView,
                findViewById(R.id.song_cur_time), findViewById(R.id.song_total_time),
                findViewById(R.id.song_seekBar), backButton, pausePlayButtom);
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

    boolean pauseFlag = false;

    public void pausePlayTrackPushed(View view) {
        if (!pauseFlag) {
            if (isHeadphonesPlugged()) {
                Log.d("PlayTrackPushed", "Try play track");
                pausePlayButtom.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                trackWorker.start(track);
                pauseFlag = true;
            } else {
                Toast.makeText(this, "Please, connect headphones", Toast.LENGTH_SHORT).show();
            }
        } else {
            pausePlayButtom.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            trackWorker.pause();
            pauseFlag = false;
        }
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
            Toast.makeText(this, "No permissions", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        trackWorker.close();
    }
}
