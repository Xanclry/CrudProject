package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(String email);
    void deleteUser(long id);
    User getUser(long id);
    List<User> getAllUsers();

}
