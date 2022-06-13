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
    private String id;
    private String textId;

    public Track() {
    }

    public Track(String name, String author, String url, String id) {
        this.name = name;
        this.author = author;
        this.url = url;
        this.id = id;
        this.textId = id + "text";
    }

    public Track(Parcel in) {
        name = in.readString();
        author = in.readString();
        url = in.readString();
        id = in.readString();
        textId = in.readString();
    }

    public Track(BufferedReader bufferedReader) throws IOException {
        name = bufferedReader.readLine();
        author = bufferedReader.readLine();
        url = bufferedReader.readLine();
        id = bufferedReader.readLine();
        textId = bufferedReader.readLine();
    }

    public void write(FileOutputStream fos) throws IOException {
        fos.write(name.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(author.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(url.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(id.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(textId.getBytes(StandardCharsets.UTF_8));
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

    public String getId() {
        return id;
    }

    public String getTextId() { return textId; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTextId(String textId) {
        this.textId = textId;
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
        parcel.writeString(id);
        parcel.writeString(textId);
    }
}
