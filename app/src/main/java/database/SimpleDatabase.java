package database;

import java.util.HashSet;

public class SimpleDatabase implements DataBase {

    static HashSet<String> emails = new HashSet<>();
    static HashSet<String> emailAndPassword = new HashSet<>();

    @Override
    public void add(User user) {
        emails.add(user.email);
        emailAndPassword.add(user.email + " " + user.password);
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
    public void remove(User user) {
        emails.remove(user.email);
        emailAndPassword.remove(user.email + " " + user.password);
    }
}
