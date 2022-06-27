package com.example.karaoke_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import database.Track;
import database.User;
import voice.TrackPlayer;
import voice.TrackPlayerSimple;

public class PlayMusicActivity extends AppCompatActivity {

    private TextView titleTV;
    private TextView curTimeTV;
    private TextView totalTimeTV;
    private SeekBar seekBar;
    private ImageView pausePlayButtom;
    private ImageView nextButtom;
    private ImageView previousButtom;

    private User user;
    private List<Track> allTracks;
    private Track curTrack;

    private TrackPlayer trackPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        titleTV = findViewById(R.id.play_music_song_title);
        curTimeTV = findViewById(R.id.play_music_cur_time);
        totalTimeTV = findViewById(R.id.play_music_total_time);
        seekBar = findViewById(R.id.play_music_seek_bar);
        pausePlayButtom = findViewById(R.id.play_music_pause_play);
        nextButtom = findViewById(R.id.play_music_next);
        previousButtom = findViewById(R.id.play_music_previous);

        titleTV.setSelected(true);

        user = getIntent().getParcelableExtra("User");
        curTrack = getIntent().getParcelableExtra("Track");
        allTracks = user.getTrackList();

        trackPlayer = new TrackPlayerSimple(getApplicationContext());

        pausePlayButtom.setOnClickListener(v -> pausePlay());
        nextButtom.setOnClickListener(v -> playNextTrack());
        previousButtom.setOnClickListener(v -> playPrevTrack());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!trackPlayer.isClosed()) {
                    int pos = trackPlayer.getPosition();
                    seekBar.setProgress(pos);
                    curTimeTV.setText(convertToTimeFormat(pos));
                }
                new Handler().postDelayed(this, 100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!trackPlayer.isClosed() && fromUser) {
                    trackPlayer.changeTime(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playTrack();
    }

    private void playTrack() {
        trackPlayer.setTrack(curTrack, null);
        titleTV.setText(curTrack.getName());
        totalTimeTV.setText(convertToTimeFormat(trackPlayer.getDuration()));
        previousButtom.setEnabled(curTrack.getId() != 0);
        nextButtom.setEnabled(curTrack.getId() != allTracks.size() - 1);

        trackPlayer.play();
        seekBar.setProgress(0);
        seekBar.setMax(trackPlayer.getDuration());
    }

    private void playNextTrack() {
        int ind = curTrack.getId() + 1;
        curTrack = allTracks.get(ind);
        playTrack();
    }

    private void playPrevTrack() {
        int ind = curTrack.getId() - 1;
        curTrack = allTracks.get(ind);
        playTrack();
    }

    private void pausePlay() {
        if (trackPlayer.isPlaying()) {
            pausePlayButtom.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            trackPlayer.pause();
        } else {
            pausePlayButtom.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
            trackPlayer.play();
        }
    }

    @SuppressLint("DefaultLocale")
    public static String convertToTimeFormat(int milsec) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(milsec) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(milsec) % TimeUnit.MINUTES.toSeconds(1));
    }
}