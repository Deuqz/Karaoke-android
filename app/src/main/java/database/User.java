package database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User implements Parcelable {
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private ArrayList<Track> trackList;

    public User(String firstName, String secondName, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
    }

    protected User(Parcel in) {
        firstName = in.readString();
        secondName = in.readString();
        email = in.readString();
        password = in.readString();
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

    @NonNull
    public String toString() {
        return firstName + " " + secondName + " " + email;
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
    }
}
