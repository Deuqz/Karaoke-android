package voice;

public interface VoiceRecorder {
    default void startRecording() {
        throw new UnsupportedOperationException();
    }

    default void pauseRecording() {
        throw new UnsupportedOperationException();
    }

    default void stopRecording() {
        throw new UnsupportedOperationException();
    }

    default String getFilePath() {
        throw new UnsupportedOperationException();
    }
}
