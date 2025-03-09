package login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginUtilsTest {

    private LoginUtils loginUtils;

    @BeforeEach
    public void setUp() {
        loginUtils = new LoginUtils();
    }

    @Test
    public void testHashPassword() {
        String password = "password123";
        String hashedPassword = loginUtils.hashPassword(password);

        assertNotNull(hashedPassword);
        assertTrue(BCrypt.checkpw(password, hashedPassword));
    }

    @Test
    public void testValidateUserValid() throws SQLException {
        String username = "test";
        String password = "1234";
        
        boolean isValid = loginUtils.validateUser(username, password);

        assertTrue(isValid);
    }

    @Test
    public void testValidateUserInvalid() throws SQLException {
        String username = "testuser";
        String password = "wrongpassword";

        boolean isValid = loginUtils.validateUser(username, password);

        assertFalse(isValid);
    }
}