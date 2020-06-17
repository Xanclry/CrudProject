package dao;

import model.User;

import java.util.List;

public interface UserDao {
    boolean createUser(User user);

    void updateUser(User user);

    void deleteUser(String email);

    void deleteUser(long id);

    void deleteAll();

    User getUser(long id);

    User getUserByEmailPassword(String email, String password);

    List<User> getAllUsers();



}
