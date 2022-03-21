package database;

public class User {
    String firstName;
    String secondName;
    String email;
    String password;

    public User(String firstName, String secondName, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
    }
}
