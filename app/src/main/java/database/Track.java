package database;

import androidx.annotation.NonNull;

public class Track {
    private String name;
    private String author;
    private String url;
    private int id;

    public Track(String name, String author, String url, int id) {
        this.name = name;
        this.author = author;
        this.url = url;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String toString() {
        return getAuthor() + " \"" + getName() + "\"";
    }
}
