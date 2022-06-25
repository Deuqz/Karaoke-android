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

import database.DataBase;
import database.FileConverter;
import database.FileEntity;
import database.FileLoadingController;
import database.FileLoadingControllerFirebase;
import database.FileLoadingControllerRedis;
import database.ReadyDatabase;
import database.Track;
import database.User;
import exceptions.NotSettedParametersException;

public class TrackWorkerSmart extends TrackWorkerSimple {
    protected final UseCaseFilesDownload downloader;
    protected final UseCaseFilesUpload uploader;
    protected final UseCaseMergeFiles merger;
    protected final ProcessingIndicator indicator;
    protected final FileLoadingController controller;
    protected final DataBase dataBase;
    protected static final String LOG_TAG = "TrackWorkerSmart";

    public TrackWorkerSmart(Context context, User user, TextView textView, TextView processView) {
        super(context, user, textView, processView);
        downloader = new UseCaseFilesDownload();
        uploader = new UseCaseFilesUpload();
        merger = new UseCaseMergeFiles();
        indicator = new ProcessingIndicator();
        dataBase = new ReadyDatabase();
        // controller = new FileLoadingControllerRedis(host, port);
        controller = new FileLoadingControllerFirebase();
    }

    protected class UseCaseFilesDownload implements Runnable {
        private String url;
        private FileEntity curFE;

        public void setUrl(String url) {
            this.url = url;
        }

        public FileEntity getFileEntity() {
            return curFE;
        }

        @Override
        public void run() {
            if (url == null) {
                throw new NotSettedParametersException("url", this.getClass().getName());
            }
            curFE = controller.download(url);
            url = null;
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

    protected static class UseCaseMergeFiles implements Runnable {
        private String newFilePath;
        private String newUrl;
        private AudioMuxer muxer;

        public void setMuxer(AudioMuxer muxer) {
            this.muxer = muxer;
            this.muxer.setWorkStatus(true);
        }

        public String getNewFilePath() {
            return newFilePath;
        }

        public String getNewUrl() { return newUrl; }

        public boolean isWorked() {
            return muxer != null && muxer.isWorked();
        }

        @Override
        public void run() {
            muxer.execute();
            newFilePath = muxer.getFileName();
            newUrl = muxer.getUrl();
        }
    }

    protected enum StepProcess {
        FIRST,
        SECOND,
        THIRD
    }

    protected class ProcessingIndicator implements Runnable {
        private StepProcess step;
        private final String processing1;
        private final String processing2;
        private final String processing3;

        public ProcessingIndicator() {
            step = StepProcess.FIRST;
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
                        step = StepProcess.SECOND;
                        break;
                    case SECOND:
                        processView.setText(processing2);
                        step = StepProcess.THIRD;
                        break;
                    case THIRD:
                        processView.setText(processing3);
                        step = StepProcess.FIRST;
                        break;
                }
                SystemClock.sleep(200);
            }
            processView.setText("");
        }
    }

    private InputStream curText;
    protected Track curTrack;

    @Override
    protected InputStream getSongText(int textId) {
        return curText;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void start(Track track) {
        curTrack = track;
        String url = track.getUrl();
        String trackPath = context.getExternalCacheDir().getAbsolutePath();
        trackPath += getTrackFileName(url);
        Log.d(LOG_TAG, "Path to file: " + trackPath);
        Log.d(LOG_TAG, String.valueOf(Files.exists(Paths.get(trackPath))));
        if (!pausePushed && !Files.exists(Paths.get(trackPath))) {
            String message = String.format("Download trackFile: %s, url: %s", track.getName(), getTrackFileName(url));
            FileEntity trackEntity = downloadData(getTrackFileName(url), message);

            Log.d(LOG_TAG, String.format("Write file %s by path %s", trackEntity.getUrl(), trackPath));
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

        String textUrl = getTrackTextFileName(url);
        String message = String.format("Download text by url: %s", textUrl);
        FileEntity textEntity = downloadData(textUrl, message);
        curText = new ByteArrayInputStream(textEntity.getData());

        super.start(track);
    }

    private FileEntity downloadData(String url, String message) {
        Log.d(LOG_TAG, message);
        downloader.setUrl(url);
        controller.setWorkStatus(true);
        Thread downloading = new Thread(downloader);
//        Thread processing = new Thread(indicator);
//        processing.start();
        downloading.start();
        indicator.run();
//        try {
//            downloading.join();
//        } catch (InterruptedException e) {
//            Log.e(LOG_TAG, "Can't wait downloading");
//            e.printStackTrace();
//        }
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
        Thread merging = new Thread(merger);
//        Thread processing = new Thread(indicator);
        merging.start();
//        processing.start();
        indicator.run();
        try {
//            merging.join();
            Files.delete(Paths.get(voicePath));
//        } catch (InterruptedException e) {
//            Log.e(LOG_TAG, "Can't join merging process");
//            e.printStackTrace();
        } catch (IOException e) {
            Log.d(LOG_TAG, "Can't delete voice file");
        }
        String filePath = merger.getNewFilePath();

        try {
            String newRecordUrl = merger.getNewUrl();

            Log.d(LOG_TAG, String.format("Upload trackFile with voice %s", filePath));
            FileEntity entity = FileConverter.convert(newRecordUrl, filePath);
            uploader.setEntity(entity);
            Thread uploading = new Thread(uploader);
//            processing = new Thread(indicator);
            uploading.start();
//            processing.start();
//            processing.join();
            indicator.run();

            String author = user.getEmail();
            int newRecordId = user.getTrackList().size();
            Track newTrack = new Track(curTrack.getName(), author, newRecordUrl, newRecordId);
            dataBase.addTrackToUser(user.getEmail(), newTrack);
            user.addTrack(newTrack);
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
        } /*catch (InterruptedException e) {
            Log.e(LOG_TAG, "Can't upload file" + filePath);
            e.printStackTrace();
        }*/
    }

    protected String getTrackFileName(String url) {
        return String.format("%s.mp3", url);
    }

    protected String getTrackTextFileName(String url) {
        return String.format("%stext.txt", url);
    }
}
