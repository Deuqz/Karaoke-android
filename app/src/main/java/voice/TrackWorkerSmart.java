package voice;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import database.FileConverter;
import database.FileEntity;
import database.FileLoadingController;
import database.FileLoadingControllerFirebase;
import database.FileLoadingControllerRedis;
import database.Track;
import database.User;
import exceptions.NotSettedParametersException;

public class TrackWorkerSmart extends TrackWorkerSimple {
    protected final UseCaseFilesDownload downloader;
    protected final UseCaseFilesUpload uploader;
    protected final UseCaseMergeFiles merger;
    protected final ProcessingIndicator indicator;
    protected final FileLoadingController controller;
    protected static final String host = "172.28.159.197";
    protected static final int port = 6379;
    protected static final String LOG_TAG = "TrackWorkerSmart";

    public TrackWorkerSmart(Context context, User user, TextView textView, TextView processView) {
        super(context, user, textView, processView);
        downloader = new UseCaseFilesDownload();
        uploader = new UseCaseFilesUpload();
        merger = new UseCaseMergeFiles();
        indicator = new ProcessingIndicator();
        // controller = new FileLoadingControllerRedis(host, port);
        controller = new FileLoadingControllerFirebase();
    }

    protected class UseCaseFilesDownload implements Runnable {
        private String id;
        private FileEntity curFE;

        public void setId(String id) {
            this.id = id;
        }

        public FileEntity getFileEntity() {
            return curFE;
        }

        @Override
        public void run() {
            if (id == null) {
                throw new NotSettedParametersException("id", this.getClass().getName());
            }
            curFE = controller.download(id);
            id = null;
        }
    }

    protected class UseCaseFilesUpload implements Runnable {
        private FileEntity entity;

        public void setEntity(FileEntity entity) {
            this.entity = entity;
        }

        @Override
        public void run() {
            if (entity == null) {
                throw new NotSettedParametersException("entity", this.getClass().getName());
            }
            controller.upload(entity);
            entity = null;
        }
    }

    protected class UseCaseMergeFiles implements Runnable {
        private String newFilePath;
        private String newId;
        private AudioMuxer muxer;

        public void setMuxer(AudioMuxer muxer) {
            this.muxer = muxer;
            this.muxer.setWorkStatus(true);
        }

        public String getNewFilePath() {
            return newFilePath;
        }

        public String getNewId() { return newId; }

        public boolean isWorked() {
            return muxer != null && muxer.isWorked();
        }

        @Override
        public void run() {
            muxer.execute();
            newFilePath = muxer.getFileName();
            newId = muxer.getId();
        }
    }

    protected enum StepLoading {
        FIRST,
        SECOND,
        THIRD
    }

    protected class ProcessingIndicator implements Runnable {
        private StepLoading step;
        private final String processing1;
        private final String processing2;
        private final String processing3;

        public ProcessingIndicator() {
            step = StepLoading.FIRST;
            String processing = "Process";
            processing1 = processing + ".";
            processing2 = processing + "..";
            processing3 = processing + "...";
        }

        @Override
        public void run() {
            while (controller.isWorked() || merger.isWorked()) {
                Log.e(LOG_TAG, (String) processView.getText());
                switch (step) {
                    case FIRST:
                        processView.setText(processing1);
                        step = StepLoading.SECOND;
                        break;
                    case SECOND:
                        processView.setText(processing2);
                        step = StepLoading.THIRD;
                        break;
                    case THIRD:
                        processView.setText(processing3);
                        step = StepLoading.FIRST;
                        break;
                }
                SystemClock.sleep(200);
            }
            processView.setText("");
        }
    }

    private InputStream curText;

    @Override
    protected InputStream getSongText(int textId) {
        return curText;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void start(Track track) {
        String trackPath = context.getExternalCacheDir().getAbsolutePath();
        trackPath += String.format("/%s", track.getId());
        Log.d(LOG_TAG, "Path: " + trackPath);
        Log.d(LOG_TAG, String.valueOf(Files.exists(Paths.get(trackPath))));
        if (!pausePushed/* && !Files.exists(Paths.get(trackPath))*/) {
            String message = String.format("Download trackFile: %s, id: %s", track.getName(), track.getId());
            FileEntity trackEntity = downloadData(track.getId(), message);

            Log.d(LOG_TAG, String.format("Write file %s by path %s", trackEntity.getId(), trackPath));
            try {
                FileOutputStream fosTrack = new FileOutputStream(trackPath);
                fosTrack.write(trackEntity.getData());
                fosTrack.flush();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(LOG_TAG, "Can't write file");
                Toast.makeText(context, "Can't download track", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.d(LOG_TAG, "Write file successfully");
        }

        FileEntity textEntity = downloadData(track.getTextId(), "Download text");
        curText = new ByteArrayInputStream(textEntity.getData());

        super.start(track);
    }

    private FileEntity downloadData(String id, String message) {
        Log.d(LOG_TAG, message);
        downloader.setId(id);
        controller.setWorkStatus(true);
        Thread t1 = new Thread(downloader);
        t1.start();
        indicator.run();
        FileEntity entity = downloader.getFileEntity();
        Log.d(LOG_TAG, "Download successfully");
        return entity;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void stop() {
        super.stop();

        String voicePath = voiceRecorder.getFilePath();
        String trackPath = trackPlayer.getFilePath();
        Log.d(LOG_TAG, String.format("Merge files %s and %s", trackPath, voicePath));
        AudioMuxer muxer = new AudioMuxerFfmpeg(trackPath, voicePath, context);
        merger.setMuxer(muxer);
        Thread t = new Thread(merger);
        t.start();
        indicator.run();
        try {
            Files.delete(Paths.get(voicePath));
        } catch (IOException e) {
            Log.d(LOG_TAG, "Can't delete voice file");
        }
        String filePath = merger.getNewFilePath();

        try {
            Log.d(LOG_TAG, String.format("Upload trackFile with voice %s", filePath));
            FileEntity entity = FileConverter.convert(merger.getNewId(), filePath);
            uploader.setEntity(entity);
            t = new Thread(uploader);
            t.start();
            indicator.run();
            Log.d(LOG_TAG, "Upload successfully");

            Log.d(LOG_TAG, String.format("Try play new track %s", filePath));

            MediaPlayer player = new MediaPlayer();
            player.setDataSource(filePath);
            player.prepare();
            Log.e(LOG_TAG, "Play recorded voice");
            player.start();

        } catch (IOException e) {
            Log.e(LOG_TAG, "Can't upload file " + filePath);
            Toast.makeText(context, "Can't upload track", Toast.LENGTH_SHORT).show();
        }
    }
}
