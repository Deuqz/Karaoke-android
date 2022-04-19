package voice;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.io.IOException;

import database.Track;

public class TrackPlayerSimple implements TrackPlayer {
    MediaPlayer mediaPlayer;
    Context context;

    public TrackPlayerSimple(Context context) {
        this.context = context;
    }

    public void setTrack(Track track) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            stop();
        }
        mediaPlayer = MediaPlayer.create(context, track.id);
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        mediaPlayer.setOnCompletionListener(mp -> stop());
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
        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
        }
        catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void close() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
