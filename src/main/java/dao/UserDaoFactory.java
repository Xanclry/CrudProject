package dao;

import dao.hibernate.HibernateUserDaoImpl;
import dao.jdbc.JdbcUserDaoImpl;
import lombok.extern.slf4j.Slf4j;
import util.PropertyReader;

@Slf4j
public class UserDaoFactory {

    private UserDaoFactory() {
    }

    public static UserDao getUserDao() {
        String daoImpl = PropertyReader.getProperty("daotype");
        UserDao dao;
        switch (daoImpl) {
            case "jdbc": {
                dao = new JdbcUserDaoImpl();
                log.info("Using JDBC user dao implementation");
            } break;
            default:
            case "hibernate": {
                dao = new HibernateUserDaoImpl();
                log.info("Using HIBERNATE user dao implementation");
            }
        }
        return dao;
    }
}
