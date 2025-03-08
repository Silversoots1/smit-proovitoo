package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DatabaseUtils;
import org.mindrot.jbcrypt.BCrypt;

public class LoginUtils {

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean validateUser(String username, String password) {
        String sql = "SELECT password FROM login WHERE username = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
                    
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return BCrypt.checkpw(password, storedPassword);
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public void addUserToDatabase(String username, String hashedPassword) {
        String sql = "INSERT INTO login (username, password) VALUES (?, ?)";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            statement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}