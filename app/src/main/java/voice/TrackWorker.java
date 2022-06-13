package voice;

import database.Track;

public interface TrackWorker {
    void start(Track track);

    void pause();

    void stop();

    void close();
}
