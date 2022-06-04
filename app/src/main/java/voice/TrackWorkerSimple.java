package voice;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karaoke_android.R;
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegLoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import database.Track;
import database.User;

public class TrackWorkerSimple implements TrackWorker{

    private User user;
    private Context context;
    private TrackPlayer trackPlayer;
    private VoiceRecorderSimple voiceRecorder;
    private boolean pausePushed;
    private TextView textView;
    private static final String LOG_TAG = "VoiceRecorderSimple";
    private FFmpeg fFmpeg;

    public TrackWorkerSimple(Context context, User user, TextView textView) {
        this.user = user;
        this.context = context;
        trackPlayer = new TrackPlayerSimple(context);
        voiceRecorder = new VoiceRecorderSimple(context, user);
        pausePushed = false;
        this.textView = textView;
            
        /*fFmpeg = FFmpeg.getInstance(context.getApplicationContext());
        try {
            fFmpeg.loadBinary(new LoadBinaryResponseHandler()*//*new FFmpegLoadBinaryResponseHandler() {
                @Override
                public void onStart() {}

                @Override
                public void onFinish() {}

                @Override
                public void onFailure() {
                    Toast.makeText(context.getApplicationContext(), "Library failed to load", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess() {
                    Toast.makeText(context.getApplicationContext(), "Library successfully loaded", Toast.LENGTH_LONG).show();
                }
            }*//*);
        } catch (FFmpegNotSupportedException e) {
            Toast.makeText(context.getApplicationContext(), "Can not load library", Toast.LENGTH_LONG).show();
            throw new RuntimeException("Can not load library");
        }*/

    }

    private class TextUpdater implements Runnable {
        Map<Integer, String> timeCodes;

        public TextUpdater(Map<Integer, String> timeCodes) {
            this.timeCodes = timeCodes;
        }

        @Override
        public void run() {
            for (Map.Entry<Integer, String> entry : timeCodes.entrySet()) {
                while(!trackPlayer.isClosed()) {
                    int x = trackPlayer.getPosition();
                    if (x == -1) { return; }
                    if (x >= entry.getKey()) { break; }
                    SystemClock.sleep(1000);
                }
                Log.e(LOG_TAG, entry.getValue());
                textView.setText(entry.getValue());
            }
        }
    }

    protected InputStream getSongText(int textId) {
        return context.getResources().openRawResource(textId);
    }

    @Override
    public void start(Track track, int textId) {
        InputStream is = getSongText(textId);
        Map<Integer, String> timeCodes = TextParser.parse(is);
        Thread t = new Thread(new TextUpdater(timeCodes));
        if (!pausePushed) {
            trackPlayer.setTrack(track);
        }
        trackPlayer.play();
        voiceRecorder.startRecording();
        t.start();
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
        /*String[] cmd = {"-i", "app/res/raw/track1.mp3", "-i", voiceRecorder.getFileName(), "-codec:a", "aac",
                "-strict", "-2", "-filter_complex \"[0:a][1:a]amix\"", "-ac", "2", "-f", "flv rtmp://myIp:1935/live/myStream" };
        try {
            fFmpeg.execute(cmd, new ExecuteBinaryResponseHandler());
        } catch (FFmpegCommandAlreadyRunningException e) {
            Log.e(LOG_TAG, "FFmpeg command already running: " + e.getMessage());
        }*/
        /*if (flag) {
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
        }*/
    }

    @Override
    public void close() {
        trackPlayer.stop();
        trackPlayer.close();
    }
}
