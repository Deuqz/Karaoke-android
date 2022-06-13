package voice;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

import database.Track;
import exceptions.NoTrackException;

public class TrackPlayerSimple implements TrackPlayer {
    protected MediaPlayer mediaPlayer;
    protected Context context;
    protected String trackPath;
    protected static final String LOG_TAG = "TrackPlayerSimple";

    public TrackPlayerSimple(Context context) {
        this.context = context;
    }

    public void setTrack(Track track, TrackWorker worker) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            stop();
        }
        trackPath = context.getExternalCacheDir().getAbsolutePath();
        trackPath += "/" + track.getId();
        Log.d(LOG_TAG, "Set track %s" + trackPath);
        mediaPlayer = new MediaPlayer();
        try {
            Log.d(LOG_TAG, "Try set data source");
            mediaPlayer.setDataSource(trackPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't set data source");
        }
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                worker.stop();
            }
        });
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't set data source");
        }
    }

    public void play() throws NoTrackException {
        if (mediaPlayer == null) {
            throw new NoTrackException();
        }
        mediaPlayer.start();
    }

    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer == null) { return; }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    public void close() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public int getPosition() {
        if (mediaPlayer == null) {
            return -1;
        }
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public boolean isClosed() {
        return mediaPlayer == null;
    }

    @Override
    public String getFilePath() {
        return trackPath;
    }
}
