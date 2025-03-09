package secured;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsTest {

    @Test
    public void testConstructorAndGetters() {
        UserDetails userDetails = new UserDetails("John Doe", "1234567890", "1,2,3", true);

        assertEquals("John Doe", userDetails.getName());
        assertEquals("1234567890", userDetails.getPhone());
        assertEquals("1,2,3", userDetails.getCarIds());
        assertTrue(userDetails.isHasLicense());
    }

    @Test
    public void testGetNameWithNull() {
        UserDetails userDetails = new UserDetails(null, "1234567890", "1,2,3", true);

        assertEquals("", userDetails.getName());
    }

    @Test
    public void testGetPhoneWithNull() {
        UserDetails userDetails = new UserDetails("John Doe", null, "1,2,3", true);

        assertEquals("", userDetails.getPhone());
    }

    @Test
    public void testGetCarIdsWithNull() {
        UserDetails userDetails = new UserDetails("John Doe", "1234567890", null, true);

        assertEquals("", userDetails.getCarIds());
    }

    @Test
    public void testIsHasLicense() {
        UserDetails userDetails = new UserDetails("John Doe", "1234567890", "1,2,3", true);

        assertTrue(userDetails.isHasLicense());
    }

    @Test
    public void testIsHasLicenseFalse() {
        UserDetails userDetails = new UserDetails("John Doe", "1234567890", "1,2,3", false);

        assertFalse(userDetails.isHasLicense());
    }
}