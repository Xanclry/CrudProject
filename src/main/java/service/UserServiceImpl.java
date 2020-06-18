package service;

import dao.UserDao;
import dao.UserDaoFactory;
import lombok.extern.slf4j.Slf4j;
import model.User;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    private UserDao userDao;

    private UserServiceImpl() {
        userDao = UserDaoFactory.getUserDao();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }


    @Override
    public void addUser(User user) {
        try {
            userDao.createUser(user);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void deleteUser(String email) {
        try {
            userDao.deleteUser(email);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void deleteUser(long id) {
        try {
            userDao.deleteUser(id);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void deleteAllUsers() {
        try {
            userDao.deleteAll();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public User getUser(long id) {
        try {
            return userDao.getUser(id);
        } catch (SQLException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public User getByEmailPassword(String email, String password) {
        return userDao.getUserByEmailPassword(email, password);
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException e) {
            log.warn(e.getMessage());
            return null;
        }
    }
}
