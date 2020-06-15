package dao;

import dao.hibernate.HibernateUserDaoImpl;
import dao.jdbc.JdbcUserDaoImpl;
import util.DBHelper;

public class UserDaoFactory {

    private UserDaoFactory() {
    }

    public static UserDao getUserDao() {
        String daoImpl = DBHelper.getDaoImpl();
        UserDao dao;
        switch (daoImpl) {
            case "jdbc": dao = new JdbcUserDaoImpl(); break;
            default:
            case "hibernate": dao = new HibernateUserDaoImpl();
        }
        return dao;
    }
}
