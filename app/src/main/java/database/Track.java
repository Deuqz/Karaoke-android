package database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class Track implements Parcelable, Serializable {
    private String name;
    private String author;
    private String url;
    private int id;

    public Track() {

    }

    public Track(String name, String author, String url, int id) {
        this.name = name;
        this.author = author;
        this.url = url;
        this.id = id;
    }

    public Track(Parcel in) {
        name = in.readString();
        author = in.readString();
        url = in.readString();
        id = in.readInt();
    }

    public Track(BufferedReader bufferedReader) throws IOException {
        name = bufferedReader.readLine();
        author = bufferedReader.readLine();
        url = bufferedReader.readLine();
        id = bufferedReader.read();
    }

    public void write(FileOutputStream fos) throws IOException {
        fos.write(name.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(author.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(url.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(String.valueOf(id).getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
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

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(author);
        parcel.writeString(url);
        parcel.writeInt(id);
    }
}