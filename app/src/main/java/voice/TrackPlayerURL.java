package voice;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

import database.Track;

public class TrackPlayerURL extends TrackPlayerSimple{
    public TrackPlayerURL(Context context) {
        super(context);
    }


    public void setTrack(Track track) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            stop();
        }
//        mediaPlayer = MediaPlayer.create(context, track.getId());
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        mediaPlayer.setOnCompletionListener(mp -> stop());
        try {
            mediaPlayer.setDataSource(track.getUrl());
            // below line is use to prepare
            // and start our media player.
            mediaPlayer.prepare();

        } catch (IOException e) {
            Log.e("TrackPlayerURL", "Can't set url resource " + e.getMessage());
        }
//        Log.e("TrackPlayerSimple", "SetTrack");
    }
}
