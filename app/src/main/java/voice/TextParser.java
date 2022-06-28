package voice;

import android.util.Log;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextParser {
    static final String LOG_TAG = "TextParser";

    public static Map<Integer, String> parse(InputStream is) {
        Map<Integer, String> result = new LinkedHashMap<>();
        Scanner scanner = new Scanner(is);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Log.d(LOG_TAG, line);
            String[] times = line.split(":");
            int min = Integer.parseInt(times[0]);
            int sec = Integer.parseInt(times[1]);
            int time = (min * 60 + sec) * 1000;
            StringBuilder text = new StringBuilder();
            try {
                line = scanner.nextLine();
                while (!line.equals("==============================")) {
                    text.append(line);
                    text.append("\n");
                    line = scanner.nextLine();
                }
            } catch (NoSuchElementException ignored) {}
            result.put(time, text.toString());
        }
        return result;
    }
}
