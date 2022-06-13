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

    static public String getNewId() {
//        Random rand = new Random();
//        String id = rand.ints('0', 'z' + 1)
//                .filter(x -> x <= '9' || ('A' <= x && x <= 'Z') || 'a' <= x)
//                .limit(40)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//        return id;
        if (x++ == 0) {
            return "c_PzRJ7NuNxoM61aTdrviq4Yx090dr45iYz4Xpu8hA";
        } else {
            return "GjKByAAhYmTeBpB4HRypw4vIknnE8Z4g5lO0f1Rg";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static public FileEntity convert(String id, String filePath) throws IOException {
        Log.d("FileConverter", "Id is " + id);
        File file = new File(filePath);
        return new FileEntity(id, Files.readAllBytes(file.toPath()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static public FileEntity convert(String filePath) throws IOException {
        return convert(getNewId(), filePath);
    }
}
