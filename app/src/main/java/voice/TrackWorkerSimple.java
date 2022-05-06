package voice;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.karaoke_android.R;

import java.io.IOException;

import database.Track;
import database.User;

public class TrackWorkerSimple implements TrackWorker{

    private User user;
    private Context context;
    private TrackPlayer trackPlayer;
    private VoiceRecorderSimple voiceRecorder;
    private boolean pausePushed;
    private static final String LOG_TAG = "VoiceRecorderSimple";

    public TrackWorkerSimple(Context context, User user) {
        this.user = user;
        this.context = context;
        trackPlayer = new TrackPlayerSimple(context);
        voiceRecorder = new VoiceRecorderSimple(context, user);
        pausePushed = false;
    }


    @Override
    public void start() {
        if (!pausePushed) {
            trackPlayer.setTrack(new Track("track1", "somebody", "", R.raw.track1));
        }
        trackPlayer.play();
        voiceRecorder.startRecording();
    }

    @Override
    public void pause() {
        trackPlayer.pause();
        voiceRecorder.stopRecording(); // Boom
        pausePushed = true;
    }

    private boolean flag = true;
    private MediaPlayer player;

    @Override
    public void stop() {
        trackPlayer.stop();
        voiceRecorder.stopRecording();
        if (flag) {
            flag = false;
            try {
                player = new MediaPlayer();
                player.setDataSource(voiceRecorder.getFileName());
                player.prepare();
                player.start();
                Log.e(LOG_TAG, "Play recorded voice");
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }
        } else if (player != null){
            player.release();
            player = null;
            Log.e(LOG_TAG, "Stop playing recorded voice");
        }
    }

    @Override
    public void close() {
        trackPlayer.stop();
        trackPlayer.close();
    }
}
