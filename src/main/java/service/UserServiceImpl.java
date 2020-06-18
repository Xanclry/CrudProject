package service;

import dao.jdbc.JdbcUserDao;
import lombok.extern.slf4j.Slf4j;
import model.User;

import java.util.List;

@Slf4j
public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    private JdbcUserDao userDao;

    private UserServiceImpl() {
        userDao = new JdbcUserDao();
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
        return userDao.getUser(id);
    }

    @Override
    public User getByEmailPassword(String email, String password) {
        return userDao.getUserByEmailPassword(email, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
