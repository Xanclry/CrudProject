package service;

import model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(String email);

    void deleteUser(long id);

    void deleteAllUsers();

    User getUser(long id);

    User getByEmailPassword(String email, String password);

    List<User> getAllUsers();

}
