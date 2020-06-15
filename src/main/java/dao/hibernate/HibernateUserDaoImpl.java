package dao.hibernate;

import dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.Collections;
import java.util.List;

@Slf4j
public class HibernateUserDaoImpl implements UserDao {

    @Override
    public boolean createUser(User user) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            log.debug("Saved: " + user.getEmail());
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
            log.debug("Saved: " + user.getEmail());
        } catch (Exception e) {
            log.warn(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(String email) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("delete from User where email= :email");
            query.setParameter("email", email);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            log.warn(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(long id) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = (User) session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            log.warn(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteAll() {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            log.warn(e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public User getUser(long id) {
        Session session = DBHelper.getSessionFactory().openSession();
        try {
            return (User) session.get(User.class, id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        Session session = DBHelper.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("select U from User C WHERE U.email = :email and C.password = :password");
            query
                    .setParameter("email", email)
                    .setParameter("password", password);
            return (User) query.uniqueResult();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = DBHelper.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from User");
            return (List<User>) query.list();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
}