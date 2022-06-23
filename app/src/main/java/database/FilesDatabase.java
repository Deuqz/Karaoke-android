package database;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FilesDatabase implements DataBase {
    private final Context context;

    public FilesDatabase(Context context) {
        this.context = context;
    }

    @Override
    public boolean add(User user) throws RuntimeException {
        String userFileName = user.getEmail();
        try (FileOutputStream fos = context.openFileOutput(userFileName, Context.MODE_PRIVATE)) {
            user.write(fos);
        } catch (IOException e) {
            context.deleteFile(userFileName);
            throw new RuntimeException("Can't add user");
        }
        return true;
    }

    @Override
    public boolean containsUser(String email) {
        File userFile = new File(context.getFilesDir(), email);
        return userFile.exists();
    }

    @Override
    public boolean containsPassword(String email, String password) throws RuntimeException {
        try (FileInputStream fr = context.openFileInput(email);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fr))) {
            reader.readLine();
            return reader.readLine().equals(password);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Such user is not exists");
        } catch (IOException e) {
            throw new RuntimeException("Can't add user");
        }
    }

    @Override
    public boolean removeUser(User user) {
        return false;
    }

    public boolean remove(User user) {
        return context.deleteFile(user.getEmail());
    }

    @Override
    public User getUser(String email) {
//        File userFile = new File(context.getFilesDir(), email);
        try (FileInputStream fr = context.openFileInput(email);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fr))) {
            reader.readLine();
            User user = new User(reader);
            user.setEmail(email);
            return user;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Such user is not exists");
        } catch (IOException e) {
            throw new RuntimeException("Can't get user");
        }
    }

    @Override
    public boolean addTrackToUser(String email, Track track) {
        return false;
    }

    @Override
    public ArrayList<Track> getDefaultTracks() {
        return null;
    }

    @Override
    public ArrayList<String> getAllUserEmails() {
        return null;
    }

    @Override
    public boolean deleteTrack(String login, String name) {
        return false;
    }

    @Override
    public ArrayList<Track> getLikes(String user) {
        return null;
    }

    @Override
    public void removeLike(Track track, String user) {

    }

    @Override
    public void addLike(Track track, String user) {

    }
}
