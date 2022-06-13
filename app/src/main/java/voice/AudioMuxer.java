package voice;

import database.Track;

public interface AudioMuxer {
    String execute();

    void setWorkStatus(boolean status);

    boolean isWorked();

    String getFileName();

    String getId();
}
