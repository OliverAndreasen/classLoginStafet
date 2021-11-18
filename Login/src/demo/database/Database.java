package demo.database;

import demo.domain.User;

import java.io.IOException;

public interface Database {

    public User findUser(String name, String password);

    public void saveUser(User user) throws IOException;

}
