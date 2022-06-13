package database;

public class FileEntity {
    private String id;
    private byte[] data;

    public FileEntity() {}

    public FileEntity(String id, byte[] data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
