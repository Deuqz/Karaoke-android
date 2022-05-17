//package database;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.os.Environment;
//
//import com.example.karaoke_android.R;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOError;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class FilesDatabase implements DataBase {
//    private final Context context;
//
//    public FilesDatabase(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public boolean add(User user) throws RuntimeException {
//        String userFileName = user.getEmail();
//        try (FileOutputStream fos = context.openFileOutput(userFileName, Context.MODE_PRIVATE)) {
//            user.write(fos);
//        } catch (IOException e) {
//            context.deleteFile(userFileName);
//            throw new RuntimeException("Can't add user");
//        }
//        return true;
//    }
//
//    @Override
//    public boolean containsUser(String email) {
//        File userFile = new File(context.getFilesDir(), email);
//        return userFile.exists();
//    }
//
//    @Override
//    public boolean containsPassword(String email, String password) throws RuntimeException {
//        try (FileInputStream fr = context.openFileInput(email);
//             BufferedReader reader = new BufferedReader(new InputStreamReader(fr))) {
//            reader.readLine();
//            return reader.readLine().equals(password);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException("Such user is not exists");
//        } catch (IOException e) {
//            throw new RuntimeException("Can't add user");
//        }
//    }
//
//    @Override
//    public boolean remove(User user) {
//        return context.deleteFile(user.getEmail());
//    }
//
//    @Override
//    public User getUser(String email) {
////        File userFile = new File(context.getFilesDir(), email);
//        try (FileInputStream fr = context.openFileInput(email);
//             BufferedReader reader = new BufferedReader(new InputStreamReader(fr))) {
//            reader.readLine();
//            User user = new User(reader);
//            user.setEmail(email);
//            return user;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException("Such user is not exists");
//        } catch (IOException e) {
//            throw new RuntimeException("Can't get user");
//        }
//    }
//}
