package com.example.karaoke_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import database.Track;
import database.User;
import voice.TrackPlayer;
import voice.TrackPlayerSimple;
import voice.VoiceRecorder;
import voice.VoiceRecorderSimple;

import androidx.fragment.app.Fragment;

public class MusicFragment extends Fragment {

    public MusicFragment() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

//    TrackPlayer trackPlayer;
//    VoiceRecorder voiceRecorder;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getActivity().setContentView(R.layout.activity_main);
//        trackPlayer = new TrackPlayerSimple(getActivity().getApplicationContext());
//        User user = getActivity().getIntent().getParcelableExtra("User");
//        voiceRecorder = new VoiceRecorderSimple(getActivity().getApplicationContext(), user);
//    }
//
//    // code below from Denis
//    private boolean pausePushed = false;
//    public void playTrackPushed(View view) {
//        if (!pausePushed) {
//            trackPlayer.setTrack(new Track("track1", "somebody", "", R.raw.track1));
//        }
//        trackPlayer.play();
//    }
//
//    public void pauseTrackPushed(View view) {
//        trackPlayer.pause();
//        pausePushed = true;
//    }
//
//    // Requesting permission to RECORD_AUDIO
//    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
//    private boolean permissionToRecordAccepted = false;
//    private String [] permissions = { Manifest.permission.RECORD_AUDIO };
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
//            permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//        }
//        if (!permissionToRecordAccepted) {
//            getActivity().finish();
//        }
//    }
//
//    public void recordPushed(View view) {
//    }
//
//    public void stopRecordPushed(View view) {
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        trackPlayer.stop();
//        trackPlayer.close();
//    }
}