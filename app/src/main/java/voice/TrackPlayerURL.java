package voice;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import javax.annotation.Nullable;

import database.FileLoadingControllerFirebase;
import database.Track;

public class TrackPlayerURL extends TrackPlayerSimple{
    public TrackPlayerURL(Activity activity) {
        super(activity);
        LOG_TAG = "TrackPlayerURL";
    }

    public void setTrack(Track track, @Nullable TrackWorker worker) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            stop();
        }
        trackPath = activity.getApplicationContext().getExternalCacheDir().getAbsolutePath();
        trackPath += String.format("/%s.mp3", track.getUrl());
        Log.d(LOG_TAG, "Set track %s" + trackPath);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (worker != null) {
                    worker.stop();
                }
            }
        });
        try {
            Uri url = new FileLoadingControllerFirebase().getURI(String.format("%s.mp3", track.getUrl()));
            mediaPlayer.setDataSource(activity, url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.e("TrackPlayerURL", "Can't set url resource " + e.getMessage());
        }
    }
}
