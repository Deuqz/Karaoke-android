package voice;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TextParser {
    static Map<Integer, String> parse(InputStream is) {
        Map<Integer, String> result = new LinkedHashMap<>();
        Scanner scanner = new Scanner(is);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] times = line.split(":");
            int min = Integer.parseInt(times[0]);
            int sec = Integer.parseInt(times[1]);
            int time = (min * 60 + sec) * 1000;
            StringBuilder text = new StringBuilder();
            line = scanner.nextLine();
            while(!line.equals("=============================")) {
                text.append(line);
                text.append("\n");
                line = scanner.nextLine();
            }
            result.put(time, text.toString());
        }
        return result;
    }
}
