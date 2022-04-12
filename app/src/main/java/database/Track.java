package database;

public class Track {
    public String name;
    public String author;
    public String url;
    public int id;

    public Track(String name, String author, String url, int id) {
        this.name = name;
        this.author = author;
        this.id = id;
    }
}
