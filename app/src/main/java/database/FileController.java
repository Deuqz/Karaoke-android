package database;

public interface FileController {
    FileEntity download(String id);

    void upload(FileEntity fileEntity);
}
