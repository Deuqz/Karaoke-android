package voice;

public interface AudioMuxer {
    String execute();

    void setWorkStatus(boolean status);

    boolean isWorked();

    String getFileName();

    String getUrl();
}
