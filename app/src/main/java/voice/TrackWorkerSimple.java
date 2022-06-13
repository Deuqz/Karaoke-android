package voice;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Map;

import database.Track;
import database.User;

public class TrackWorkerSimple implements TrackWorker {

    protected User user;
    protected Context context;
    protected TrackPlayer trackPlayer;
    protected VoiceRecorder voiceRecorder;
    protected boolean pausePushed;
    protected TextView textView;
    protected TextView processView;
    protected static final String LOG_TAG = "TrackWorkerSimple";

    protected Track curTrack;

    public TrackWorkerSimple(Context context, User user, TextView textView, TextView processView) {
        this.user = user;
        this.context = context;
        trackPlayer = new TrackPlayerSimple(context);
        voiceRecorder = new VoiceRecorderSimple(context, user);
        pausePushed = false;
        this.textView = textView;
        this.processView = processView;
    }

    protected class TextUpdater implements Runnable {
        Map<Integer, String> timeCodes;

        public TextUpdater(Map<Integer, String> timeCodes) {
            this.timeCodes = timeCodes;
        }

        @Override
        public void run() {
            for (Map.Entry<Integer, String> entry : timeCodes.entrySet()) {
                while (!trackPlayer.isClosed()) {
                    if (!pausePushed) {
                        int x = trackPlayer.getPosition();
                        if (x == -1) {
                            return;
                        }
                        if (x >= entry.getKey()) {
                            break;
                        }
                    }
                    SystemClock.sleep(1000);
                }
                textView.setText(entry.getValue());
            }
            textView.setText("");
        }
    }

    protected InputStream getSongText(int textId) {
        return context.getResources().openRawResource(textId);
    }

    @Override
    public void start(Track track) {
        curTrack = track;
        Thread t = null;
        if (!pausePushed) {
            Log.d(LOG_TAG, "Parse text");
            InputStream is = getSongText(3);
            Map<Integer, String> timeCodes = TextParser.parse(is);
            t = new Thread(new TextUpdater(timeCodes));
            trackPlayer.setTrack(track, this);
            Log.d(LOG_TAG, "Start work");
        } else {
            Log.d(LOG_TAG, "Continue work");
        }
        trackPlayer.play();
        voiceRecorder.startRecording();
        if (!pausePushed) {
            t.start();
        }
        pausePushed = false;
    }

    @Override
    public void pause() {
        trackPlayer.pause();
        voiceRecorder.pauseRecording();
        pausePushed = true;
    }

    @Override
    public void stop() {
        trackPlayer.stop();
        voiceRecorder.stopRecording();
        pausePushed = false;
    }

    @Override
    public void close() {
        trackPlayer.stop();
        trackPlayer.close();
    }
}
