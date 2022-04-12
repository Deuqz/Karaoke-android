package voice;

import database.Track;

public interface TrackPlayer {
    default void setTrack(Track track) {
        throw new UnsupportedOperationException();
    }

    default void play() {
        throw new UnsupportedOperationException();
    }

    default void pause() {
        throw new UnsupportedOperationException();
    }

    default void stop() {
        throw new UnsupportedOperationException();
    }

    default void close() {
        throw new UnsupportedOperationException();
    }
}
