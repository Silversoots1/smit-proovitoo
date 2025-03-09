package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {

    private static final String PROPERTIES_FILE = "db.properties";
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    static {
        try (InputStream input = DatabaseUtils.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTIES_FILE);
            }
            properties.load(input);
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}