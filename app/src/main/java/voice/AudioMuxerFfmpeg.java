package voice;

import android.content.Context;
import android.util.Log;

import com.arthenica.mobileffmpeg.FFmpeg;

import java.util.concurrent.atomic.AtomicBoolean;

import database.FileConverter;
import database.Track;

public class AudioMuxerFfmpeg implements AudioMuxer {

    private final String LOG_TAG = "AudioMuxerFfmpeg";

    private final String[] command;

    private final String newFile;
    private final String newId;

    private AtomicBoolean isWorked = new AtomicBoolean(false);

    public AudioMuxerFfmpeg(String trackFile, String voiceFile, Context context) {
        newId = FileConverter.getNewId();
        newFile = context.getExternalCacheDir().getAbsolutePath()
                + String.format("/%s.mp3", newId);
        command = new String[]{"-i", trackFile,
                "-i", voiceFile,
                "-filter_complex",
                "amix=inputs=2:duration=first:dropout_transition=3",
                newFile};
    }

    @Override
    public String execute() {
        isWorked.set(true);
        Log.d(LOG_TAG, String.format("Try merge files to %s", newFile));
        FFmpeg.execute(command);
        isWorked.set(false);
        return newFile;
    }

    @Override
    public void setWorkStatus(boolean status) {
        isWorked.set(status);
    }

    @Override
    public boolean isWorked() {
        return isWorked.get();
    }

    @Override
    public String getFileName() {
        return newFile;
    }

    @Override
    public String getId() { return newId; }
}
