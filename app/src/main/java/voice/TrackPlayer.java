package voice;

import database.Track;

public interface TrackPlayer {
    default void setTrack(Track track, TrackWorker worker) {
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

    default boolean isClosed() {
        throw new UnsupportedOperationException();
    }

    default int getPosition() {
        throw new UnsupportedOperationException();
    }

    default String getFilePath() {
        throw new UnsupportedOperationException();
    }
}
