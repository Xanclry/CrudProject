package dao.jdbc;

import dao.UserDao;
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
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(id, email, password);

    }

    public boolean createUser(User user) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT into users (email, password) value (?, ?)")) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            log.warn(e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void updateUser(User user) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("update users set email = ?, password = ? where id = ?")) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            log.warn(e.getMessage());
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }


    public void deleteUser(String email) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Delete from users where email = ?")) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            log.warn(e.getMessage());
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }


    public void deleteUser(long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Delete from users where id = ?")) {
            connection.setAutoCommit(false);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            log.warn(e.getMessage());
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }


    public void deleteAll() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("Delete from users")) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            log.warn(e.getMessage());
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }


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
        }
        return result;
    }

    public User getUserByEmailPassword(String email, String password) {
        User result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("select * from users where email=? and password=?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result = mapUser(resultSet);
                }
            }
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
        return result;
    }


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
        }
        return users;
    }
}
