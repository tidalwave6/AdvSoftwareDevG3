package com.webapp;

import com.g3app.model.StaffUser;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StaffUserTest {

    private StaffUser staffUser;

    @Before
    public void setUp() {
        staffUser = new StaffUser("John", "Doe", "john.doe@example.com", "password123", "1990-01-01", "+61123456789",
                                  "123 Example Street", "Sydney", "2000", "Australia", "staff", "active");
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", staffUser.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", staffUser.getLastName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", staffUser.getEmail());
    }

    @Test
    public void testGetPhone() {
        assertEquals("+61123456789", staffUser.getPhone());
    }

    @Test
    public void testGetRole() {
        assertEquals("staff", staffUser.getRole());
    }

    @Test
    public void testGetAccountStatus() {
        assertEquals("active", staffUser.getAccountStatus());
    }

    @Test
    public void testSetFirstName() {
        staffUser.setFirstName("Jane");
        assertEquals("Jane", staffUser.getFirstName());
    }

    @Test
    public void testSetEmail() {
        staffUser.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", staffUser.getEmail());
    }
}
