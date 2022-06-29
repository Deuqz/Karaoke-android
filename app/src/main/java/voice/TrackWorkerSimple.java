package voice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import database.Track;
import database.User;

public class TrackWorkerSimple implements TrackWorker {

    protected User user;
    protected Activity activity;
    protected TrackPlayer trackPlayer;
    protected VoiceRecorder voiceRecorder;
    protected boolean pausePushed;
    protected TextView textView;
    protected TextView processView;
    protected static final String LOG_TAG = "TrackWorkerSimple";

    protected Track curTrack;

    private final TextView curTimeTV;
    private final TextView totalTimeTV;
    private final SeekBar seekBar;

    public TrackWorkerSimple(Activity activity, User user, TextView textView, TextView processView,
                             TextView curTimeTV, TextView totalTimeTV, SeekBar seekBar) {
        this.user = user;
        this.activity = activity;
        trackPlayer = new TrackPlayerSimple(activity);
        voiceRecorder = new VoiceRecorderSimple(activity.getApplicationContext(), user);
        pausePushed = false;

        this.textView = textView;
        this.processView = processView;
        this.textView.setGravity(Gravity.CENTER_HORIZONTAL);
        this.processView.setGravity(Gravity.CENTER_HORIZONTAL);

        this.curTimeTV = curTimeTV;
        this.totalTimeTV = totalTimeTV;
        this.seekBar = seekBar;
        this.seekBar.setClickable(false);
    }

    protected class UIUpdater implements Runnable {
        private final Iterator<Map.Entry<Integer, String>> it;
        private Map.Entry<Integer, String> curEntry;

        public UIUpdater(Map<Integer, String> timeCodes) {
            it = timeCodes.entrySet().iterator();
            curEntry = it.next();
        }

        @Override
        public void run() {
            if (!trackPlayer.isClosed()) {
                int pos = trackPlayer.getPosition();
                seekBar.setProgress(pos);
                curTimeTV.setText(convertToTimeFormat(pos));
                if (pos >= curEntry.getKey()) {
                    textView.setText(curEntry.getValue());
                    if (it.hasNext()) {
                        curEntry = it.next();
                    }
                }
                new Handler().postDelayed(this, 100);
            } else {
                new Handler().removeCallbacks(this);
            }
        }
    }

    protected InputStream getSongText(int textId) {
        return activity.getApplicationContext().getResources().openRawResource(textId);
    }

    @Override
    public void start(Track track) {
        curTrack = track;
        Map<Integer, String> timeCodes = null;
        if (!pausePushed) {
            Log.d(LOG_TAG, "Parse text");
            InputStream is = getSongText(3);
            // timeCodes = TextParser.parse(is); // Simple format
            timeCodes = TextParser.parseJSON(is);
            trackPlayer.setTrack(track, this);
            Log.d(LOG_TAG, "Start work");
        } else {
            Log.d(LOG_TAG, "Continue work");
        }
        trackPlayer.play();
        totalTimeTV.setText(convertToTimeFormat(trackPlayer.getDuration()));
        seekBar.setMax(trackPlayer.getDuration());
        voiceRecorder.startRecording();
        if (!pausePushed) {
            assert timeCodes != null;
            activity.runOnUiThread(new UIUpdater(timeCodes));
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

    @SuppressLint("DefaultLocale")
    private static String convertToTimeFormat(int milsec) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(milsec) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(milsec) % TimeUnit.MINUTES.toSeconds(1));
    }
}
