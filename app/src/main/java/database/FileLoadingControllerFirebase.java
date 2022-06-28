package database;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.atomic.AtomicBoolean;

public class FileLoadingControllerFirebase implements FileLoadingController {
    private final StorageReference storageRef;
    private final AtomicBoolean isWorked = new AtomicBoolean(false);
    private static final String LOG_TAG = "FileLoadingControllerFirebase";

    public FileLoadingControllerFirebase() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    @Override
    public FileEntity download(String id) {
        FileEntity entity = new FileEntity();
        entity.setId(id);
        Log.d(LOG_TAG, String.format("try upload file %s", id));
        Task<byte[]> downloadTask = storageRef.child(id).getBytes(Long.MAX_VALUE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] data) {
                        Log.d(LOG_TAG, "Download successfully");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(LOG_TAG, "Download failed");
                    }
                });
        while(!downloadTask.isComplete()) {
            SystemClock.sleep(1000);
        }
        entity.setData(downloadTask.getResult());
        isWorked.set(false);
        return entity;
    }

    @Override
    public void upload(FileEntity fileEntity) {
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("audio/mpeg")
                .build();
        StorageReference file = storageRef.child(String.format("%s.mp3", fileEntity.getUrl()));
        Log.d(LOG_TAG, String.format("Try upload file %s", file.getName()));
        UploadTask uploadTask = file.putBytes(fileEntity.getData(), metadata);
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                Log.d(LOG_TAG, "Upload is " + progress + "% done");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e(LOG_TAG, "Uploading failed");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d(LOG_TAG, "Upload successfully");
            }
        });
        while (!uploadTask.isComplete()) {
            SystemClock.sleep(1000);
        }
        Log.d(LOG_TAG, "Upload successfully");
        isWorked.set(false);
    }

    @Override
    public boolean isWorked() {
        return isWorked.get();
    }

    @Override
    public void setWorkStatus(boolean status) {
        isWorked.set(status);
    }

    public Uri getURI(String filename) {
        Task<Uri> urlTask = storageRef.child(filename).getDownloadUrl();
        while (!urlTask.isComplete()) {
        }
        return urlTask.getResult();
    }
}
