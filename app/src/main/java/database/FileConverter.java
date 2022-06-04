package database;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class FileConverter {
    private static String getId() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 40; ++i) {
            id.append(rand.nextInt(127));
        }
        return id.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static public FileEntity convert(String filePath) throws IOException {
        String id = getId();
        Log.e("FileConverter", "Id is " + id);
        File file = new File(filePath);
        return new FileEntity(id, Files.readAllBytes(file.toPath()));
    }
}
