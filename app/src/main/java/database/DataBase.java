package database;

public interface DataBase {

    //    add user to dataBase
    void add(User user);

    //    is user correct
    boolean containsUser(String email);

    //    is password correct
    boolean containsPassword(String email, String password);

    //    remove user from database
    void remove(User user);
}
