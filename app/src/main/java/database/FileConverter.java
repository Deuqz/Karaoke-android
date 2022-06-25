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
    static int x = 0;

    static public String getNewUrl() {
        Random rand = new Random();
        String url = rand.ints('0', 'z' + 1)
                .filter(x -> x <= '9' || ('A' <= x && x <= 'Z') || 'a' <= x)
                .limit(40)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return url;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static public FileEntity convert(String url, String filePath) throws IOException {
        Log.d("FileConverter", "Url is " + url);
        File file = new File(filePath);
        return new FileEntity(url, Files.readAllBytes(file.toPath()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static public FileEntity convert(String filePath) throws IOException {
        return convert(getNewUrl(), filePath);
    }
}
