package util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBHelper {

    private DBHelper() {
    }

    /**
     * Creates new connection for jdbc dao implementation
     *
     * @return connection
     */
    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("crudproject?").                //db name
                    append("user=root&").          //login
                    append("password=root&").       //password
                    append("serverTimezone=UTC");       //password

            log.info("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            log.warn(e.getMessage());
            throw new IllegalStateException();
        }
    }


}
