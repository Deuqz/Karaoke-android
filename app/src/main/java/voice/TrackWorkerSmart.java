package voice;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

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

    protected final Button backButton;
    protected final ImageView playPauseButton;

    public TrackWorkerSmart(Activity activity, User user, TextView textView, TextView processView, TextView curTimeTV,
                            TextView totalTimeTV, SeekBar seekBar, Button backButton, ImageView playPauseButton) {
        super(activity, user, textView, processView, curTimeTV, totalTimeTV, seekBar);
        downloader = new UseCaseFilesDownload();
        uploader = new UseCaseFilesUpload();
        merger = new UseCaseMergeFiles();
        indicator = new ProcessingIndicator();
        dataBase = new ReadyDatabase();
        // controller = new FileLoadingControllerRedis(host, port);
        controller = new FileLoadingControllerFirebase();

        this.backButton = backButton;
        this.playPauseButton = playPauseButton;
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
            if (controller.isWorked() || merger.isWorked()) {
                Log.d(LOG_TAG, (String) processView.getText());
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
            } else {
                processView.setText(curTrack.getName());
            }
        }
    }

    protected class DownloadTask extends AsyncTask<Void, Void, Void> {
        private Track track;
        Consumer<Track> func;

        public void setTrack(Track track) {
            this.track = track;
        }

        public void setFunc(Consumer<Track> func) {
            this.func = func;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            indicator.run();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            backButton.setEnabled(false);
            playPauseButton.setEnabled(false);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Void doInBackground(Void... voids) {
            curTrack = track;
            String url = track.getUrl();
            Context context = activity.getApplicationContext();
            String trackPath = context.getExternalCacheDir().getAbsolutePath();
            trackPath += "/" + getTrackFileName(url);
            Log.d(LOG_TAG, "Path to file: " + trackPath);
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
                    return null;
                }
                Log.d(LOG_TAG, "Write file successfully");
            }

            String textUrl = getTrackTextFileName(url);
            String message = String.format("Download text by url: %s", textUrl);
            FileEntity textEntity = downloadData(textUrl, message);
            curText = new ByteArrayInputStream(textEntity.getData());
            return null;
        }

        private FileEntity downloadData(String url, String message) {
            Log.d(LOG_TAG, message);
            downloader.setUrl(url);
            controller.setWorkStatus(true);
            Thread downloading = new Thread(downloader);
            downloading.start();
            while(controller.isWorked()) {
                publishProgress();
            }
            FileEntity entity = downloader.getFileEntity();
            Log.d(LOG_TAG, "Download successfully");
            return entity;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            backButton.setEnabled(true);
            playPauseButton.setEnabled(true);
            func.accept(track);
        }
    }

    protected class UploadTask extends AsyncTask<Void, Void, Void> {
        Runnable func;

        public void setFunc(Runnable func) {
            this.func = func;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            indicator.run();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            backButton.setEnabled(false);
            playPauseButton.setEnabled(false);
            func.run();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Void doInBackground(Void... voids) {
            String voicePath = voiceRecorder.getFilePath();
            String trackPath = trackPlayer.getFilePath();
            Log.d(LOG_TAG, String.format("Merge files %s and %s", trackPath, voicePath));
            AudioMuxer muxer = new AudioMuxerFfmpeg(trackPath, voicePath, activity.getApplicationContext());
            merger.setMuxer(muxer);
            Thread merging = new Thread(merger);
            merging.start();
            while(merger.isWorked()) {
                publishProgress();
            }
            String filePath = merger.getNewFilePath();

            try {
                String newRecordUrl = merger.getNewUrl();
                Log.d(LOG_TAG, String.format("Upload trackFile with voice %s", filePath));
                FileEntity entity = FileConverter.convert(newRecordUrl, filePath);
                uploader.setEntity(entity);
                Thread uploading = new Thread(uploader);
                uploading.start();
                while(controller.isWorked()) {
                    publishProgress();
                }

                Log.e(LOG_TAG, String.valueOf(user.getTrackList().size()));
                String author = user.getEmail();
                int newRecordId = user.getTrackList().size();
                Track newTrack = new Track(curTrack.getName(), author, newRecordUrl, newRecordId);
                dataBase.addTrackToUser(user.getEmail(), newTrack);
                user.addTrack(newTrack);
                Log.e(LOG_TAG, String.valueOf(user.getTrackList().size()));
                Log.d(LOG_TAG, "Upload successfully");
            } catch (IOException e) {
                Log.e(LOG_TAG, "Can't upload file " + filePath);
                Toast.makeText(activity.getApplicationContext(), "Can't upload track", Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    Files.delete(Paths.get(voicePath));
                    Files.delete(Paths.get(trackPath));
                    Files.delete(Paths.get(filePath));
                } catch (IOException e) {
                    Log.d(LOG_TAG, "Can't delete files");
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            backButton.setEnabled(true);
            playPauseButton.setEnabled(true);
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
        DownloadTask task = new DownloadTask();
        task.setTrack(track);
        task.setFunc(super::start);
        task.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void stop() {
        UploadTask task = new UploadTask();
        task.setFunc(super::stop);
        task.execute();
    }

    protected String getTrackFileName(String url) {
        return String.format("%s.mp3", url);
    }

    protected String getTrackTextFileName(String url) {
        // return String.format("%stext.txt", url); // Simple format
        return String.format("%stext.json", url); // Json format
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void close() {
        super.close();
        Path voicePath = Paths.get(voiceRecorder.getFilePath());
        Path trackPath = Paths.get(trackPlayer.getFilePath());
        try {
            if (Files.exists(voicePath)) {
                Files.delete(voicePath);
            }
            if (Files.exists(trackPath)) {
                Files.delete(trackPath);
            }
        } catch (IOException e) {
            Log.d(LOG_TAG, "Can't delete files");
        }
    }
}
