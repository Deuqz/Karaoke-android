package database;

import java.io.IOException;
import java.util.ArrayList;

public interface DataBase {

    //    add user to dataBase
    public boolean add(User user) throws IOException;

    //    is user correct
    public boolean containsUser(String email);

    //    is password correct
    public boolean containsPassword(String email, String password);

    //    remove user from database
    public boolean removeUser(User user);

    //    get user from database
    public default User getUser(String email) {
        throw new UnsupportedOperationException();
    }

    public boolean addTrackToUser(String email, Track track);

    public ArrayList<Track> getDefaultTracks();

    public ArrayList<String> getAllUserEmails();

    public boolean deleteTrack(String login, String name);

    public void addLike(Track track, String user);

    public void removeLike(Track track, String user);

    public ArrayList<Track> getLikes(String user);
}
