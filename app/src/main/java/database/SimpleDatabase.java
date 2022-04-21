package database;

import java.util.HashSet;

public class SimpleDatabase implements DataBase {

    static HashSet<String> emails = new HashSet<>();
    static HashSet<String> emailAndPassword = new HashSet<>();

    @Override
    public boolean add(User user) {
        boolean result = emails.add(user.email);
        result &= emailAndPassword.add(user.email + " " + user.password);
        return result;
    }

    @Override
    public boolean containsUser(String email) {
        return emails.contains(email);
    }

    @Override
    public boolean containsPassword(String email, String password) {
        return emailAndPassword.contains(email + " " + password);
    }

    @Override
    public boolean remove(User user) {
        boolean result = emails.remove(user.email);
        result &= emailAndPassword.remove(user.email + " " + user.password);
        return result;
    }
}
