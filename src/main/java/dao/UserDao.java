package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    boolean createUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(String email) throws SQLException;

    void deleteUser(long id) throws SQLException;

    void deleteAll() throws SQLException;

    User getUser(long id);

    User getUserByEmailPassword(String email, String password);

    List<User> getAllUsers();



}
