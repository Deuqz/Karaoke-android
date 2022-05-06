package voice;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;
import android.widget.Toast;

import com.example.karaoke_android.R;

import java.io.IOException;

import database.Track;
import database.User;

public class VoiceRecorderSimple implements VoiceRecorder {
    private MediaRecorder recorder;
    private final Context context;
    private final User user;
    private String fileName;
    private static final String LOG_TAG = "VoiceRecorderSimple";

    public VoiceRecorderSimple(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        fileName = context.getExternalCacheDir().getAbsolutePath();
        fileName += "/" + user.getEmail() + 1 + ".3gp";
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        recorder.start();
        Log.e(LOG_TAG, "start recording");
    }

    private boolean flag = true;
    MediaPlayer player;

    public void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
            Log.e(LOG_TAG, "stop recording");
        }
        if (flag) {
            flag = false;
            try {
                player = new MediaPlayer();
                player.setDataSource(fileName);
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
}
