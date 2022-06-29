package voice;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;


public class TextParser {
    static final String LOG_TAG = "TextParser";

    private static Integer convert(String line) {
        String[] times = line.split(":");
        int min = Integer.parseInt(times[0]);
        int sec = Integer.parseInt(times[1]);
        return (min * 60 + sec) * 1000;
    }

    public static Map<Integer, String> parse(InputStream is) {
        Map<Integer, String> result = new LinkedHashMap<>();
        Scanner scanner = new Scanner(is);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Log.d(LOG_TAG, line);
            int time = convert(line);
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


    public static Map<Integer, String> parseJSON(InputStream is) {
        Map<Integer, String> result = new LinkedHashMap<>();
        String jsonStr = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        try {
            JSONObject info = new JSONObject(jsonStr);
            JSONArray texts = (JSONArray) info.get("text");
            JSONArray timeCodes = (JSONArray) info.get("time_codes");
            for (int i = 0; i < texts.length(); ++i){
                String text = texts.getString(i);
                String time = timeCodes.getString(i);
                Log.d(LOG_TAG, time);
                result.put(convert(time), text);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Can't parse json");
            e.printStackTrace();
        }
        return result;
    }
}
