package database;

public interface FileLoadingController {
    FileEntity download(String id);

    void upload(FileEntity fileEntity);

    boolean isWorked();

    void setWorkStatus(boolean status);
}
