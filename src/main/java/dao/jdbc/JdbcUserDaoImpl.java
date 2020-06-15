package dao.jdbc;

import dao.UserDao;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JdbcUserDaoImpl implements UserDao {

    private Connection connection = DBHelper.getMysqlConnection();

    private User mapUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String role = resultSet.getString("role");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(id, role, email, password);

    }

    @Override
    public boolean createUser(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT into users (role, email, password) value (?, ?, ?)")) {
            preparedStatement.setString(1, user.getRole());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.warn(e.getMessage());
            return false;
        }
    }

    @SneakyThrows
    @Override
    public void updateUser(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("update users set role = ?, email = ?, password = ? where id = ?")) {
            preparedStatement.setString(1, user.getRole());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    @Override
    public void deleteUser(String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Delete from users where email = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    @Override
    public void deleteUser(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Delete from users where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Delete from users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public User getUser(long id) {
        User result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("select * from users where id=?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result = mapUser(resultSet);
                }
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw e;
        }
        return result;
    }

    @SneakyThrows
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("select * from users")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(mapUser(resultSet));
                }
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
            throw e;
        }
        return users;
    }
}
