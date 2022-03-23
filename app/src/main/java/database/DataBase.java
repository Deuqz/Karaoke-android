package database;

import java.io.IOException;

public interface DataBase {

    //    add user to dataBase
    boolean add(User user) throws IOException;

    //    is user correct
    boolean containsUser(String email);

    //    is password correct
    boolean containsPassword(String email, String password);

    //    remove user from database
    boolean remove(User user);
}
