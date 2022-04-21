package database;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String firstName;
    public String secondName;
    public String email;
    public String password;

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
