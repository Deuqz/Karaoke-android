package voice;

import database.Track;

public interface TrackWorker {
    void start(Track track, int track3text);

    void pause();

    void stop();

    void close();
}
