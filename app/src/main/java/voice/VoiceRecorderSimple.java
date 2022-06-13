package voice;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;
import java.util.Random;

import database.FileConverter;
import database.User;

public class VoiceRecorderSimple implements VoiceRecorder {
    private MediaRecorder recorder;
    private final Context context;
    private final User user;
    private String filePath;
    private static final String LOG_TAG = "VoiceRecorderSimple";

    public VoiceRecorderSimple(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    public void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        filePath = context.getExternalCacheDir().getAbsolutePath();
//        filePath += "/" + FileConverter.getNewId() + ".mp3";
        filePath += "/" + user.getEmail() + "_" + FileConverter.getNewId() + ".mp3";
        recorder.setOutputFile(filePath);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        recorder.start();
        Log.d(LOG_TAG, "start recording");
    }


    public void pauseRecording() {
        if (recorder != null) {
            recorder.pause();
        }
    }

    public void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
            Log.d(LOG_TAG, "stop recording");
            /*try {
                MediaPlayer player = new MediaPlayer();
                player.setDataSource(filePath);
                player.prepare();
                player.start();
                Log.e(LOG_TAG, "Play recorded voice");
                while (player.isPlaying()) {}
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }*/
        }
    }

    @Override
    public String getFilePath(){
        return filePath;
    }
}
