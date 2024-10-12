package com.webapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.g3app.dao.DBManager;
import com.g3app.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserTest {

    private User user;
    private User updatedUser;
    private DBManager dbManager;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        // create the user object with some data before each test
        user = new User("John", "Book", "john.book@books.com", "password", "1990-01-01", "1234567890", "123 Book St", "Book City", "1", "Book");

        // create the database connection and DBManager
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "root");
        dbManager = new DBManager(connection);

        // ensure the user does not exist before adding
        dbManager.deleteUserByEmail(user.getEmail()); // clean up any existing users
        dbManager.addUser(user); // add the new user to the database
    }

    // verify that the user is created correctly
    @Test
    public void testUserCreation() {
        Assert.assertNotNull(user);
        Assert.assertEquals("John", user.getFirstName());
        Assert.assertEquals("Book", user.getLastName());
        Assert.assertEquals("john.book@books.com", user.getEmail());
    }

    @Test
    public void testUpdateUserDetails() throws Exception {
        // update user details
        String newEmail = "john.bigbooks@books.com";
        String newPassword = "newSecurePassword123"; // New password
        boolean updated = dbManager.updateUserDetails(user.getEmail(), "John", "Book", newEmail, newPassword, "1990-01-01", "9876543210", "456 Book St", "Book", "1", "Book");

        // ensure the update method returned true
        Assert.assertTrue("User details should be updated successfully.", updated);

        // fetch the updated user from the database
         updatedUser = dbManager.findUser(newEmail, newPassword);  // Use new password to fetch the user

        // verify the details were updated correctly
        Assert.assertNotNull("Updated user can not be null.", updatedUser);
        Assert.assertEquals("John", updatedUser.getFirstName());
        Assert.assertEquals("Book", updatedUser.getLastName());
        Assert.assertEquals(newEmail, updatedUser.getEmail());
        Assert.assertEquals("9876543210", updatedUser.getPhone());
        Assert.assertEquals("456 Book St", updatedUser.getAddress());
    }

    @Test
    public void testDeleteUserByEmail() throws SQLException {
        // delete the user using the original email
        boolean result = dbManager.deleteUserByEmail(user.getEmail());

        // ensure deletion was successful
        Assert.assertTrue("User should be deleted successfully.", result);

        // check that the user no longer exists in the database
        User deletedUser = dbManager.findUser(user.getEmail(), user.getPassword());
        Assert.assertNull("User should not be found after deletion.", deletedUser);
    }

    // close the database connection after tests
    @org.junit.After
    public void tearDown() throws SQLException {
        dbManager.deleteUserByEmail(user.getEmail()); // Remove the user to avoid conflicts in future tests
        
        // only delete updatedUser if it was created in the test
        if (updatedUser != null) {
            dbManager.deleteUserByEmail(updatedUser.getEmail()); // Remove updated user to avoid conflicts in future tests
        }

        // close the connection
        if (connection != null) {
            connection.close();
        }
    }
}
