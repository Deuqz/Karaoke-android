package database;

public class FileEntity {
    private String url;
    private byte[] data;

    public FileEntity() {}

    public FileEntity(String url, byte[] data) {
        this.url = url;
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public byte[] getData() {
        return data;
    }

    public void setId(String url) {
        this.url = url;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
