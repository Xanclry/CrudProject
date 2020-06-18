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
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
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
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
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
        Transaction transaction = session.getTransaction();
        try {
            Query query = session.createQuery("delete from User where email= :email");
            query.setParameter("email", email);
            transaction.begin();
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
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
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
        Transaction transaction = session.getTransaction();
        try {
            Query query = session.createQuery("delete from User");
            transaction.begin();
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
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            User user = (User) session.get(User.class, id);
            transaction.commit();
            return user;
        } catch (Exception e) {
            log.warn(e.getMessage());
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserByEmailPassword(String email, String password) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            Query query = session.createQuery("from User WHERE email = :email and password = :password");
            query
                    .setParameter("email", email)
                    .setParameter("password", password);
            transaction.begin();
            User user = (User) query.uniqueResult();
            transaction.commit();
            return user;
        } catch (Exception e) {
            log.warn(e.getMessage());
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            Query query = session.createQuery("from User");
            transaction.begin();
            List<User> list = query.list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            log.warn(e.getMessage());
            transaction.rollback();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
}
