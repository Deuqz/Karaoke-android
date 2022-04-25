package database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class User implements Parcelable, Serializable {
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private int trackListSize;
    private ArrayList<Track> trackList;

    public User(String firstName, String secondName, String email, String password, ArrayList<Track> trackList) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.trackListSize = trackList.size();
        this.trackList = trackList;
    }

    public User(String firstName, String secondName, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.trackListSize = 0;
        this.trackList = new ArrayList<>();
    }

    protected User(Parcel in) {
        firstName = in.readString();
        secondName = in.readString();
        email = in.readString();
        password = in.readString();
        trackListSize = in.readInt();
        trackList = new ArrayList<>();
        for (int i = 0; i < trackListSize; i++) {
            trackList.add(new Track(in));
        }
    }

    public User(BufferedReader reader) throws IOException {
        password = reader.readLine();
        firstName = reader.readLine();
        secondName = reader.readLine();
        trackListSize = reader.read();
        trackList = new ArrayList<>();
        for (int i = 0; i < trackListSize; i++) {
            trackList.add(new Track(reader));
        }
    }

    public void write(FileOutputStream fos) throws IOException {
        fos.write(email.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(password.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(firstName.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(secondName.getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        fos.write(String.valueOf(trackListSize).getBytes(StandardCharsets.UTF_8));
        fos.write('\n');
        for (int i = 0; i < trackListSize; i++) {
            trackList.get(i).write(fos);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getTrackListSize() {
        return trackListSize;
    }

    public ArrayList<Track> getTrackList() {
        return trackList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addTrack(Track track) {
        trackList.add(track);
    }

    public void removeTrack(Track track) {
        trackList.remove(track);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(secondName);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeInt(trackListSize);
        for (int j = 0; j < trackListSize; j++) {
            trackList.get(j).writeToParcel(parcel, i);
        }
    }
}
