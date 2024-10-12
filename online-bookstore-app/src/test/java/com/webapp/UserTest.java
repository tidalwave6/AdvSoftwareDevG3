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

    private DBManager dbManager;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "root");
        dbManager = new DBManager(connection);
        if(dbManager.findUser("john.book@books.com")!=null){dbManager.deleteUserByEmail("john.book@books.com");}
    }

    @Test
    public void testUserCreation() throws Exception{
        User user = new User("John", "Book", "john.book@books.com", "password", "123 Book St");
        dbManager.addUser(user);
        String email = "john.book@books.com";
        
        User createdUser = dbManager.findUser(email);
        Assert.assertNotNull(createdUser);
        Assert.assertEquals("John", createdUser.getFirstName());
        Assert.assertEquals("Book", createdUser.getLastName());
        Assert.assertEquals("john.book@books.com", createdUser.getEmail());
        dbManager.deleteUserByEmail(email);
    }

    @Test
    public void testUpdateUserDetails() throws Exception {
        User user = new User("John", "Book", "john.book@books.com", "password", "123 Book St");
        dbManager.addUser(user);
        String email = "john.book@books.com";
        User updatedUser = new User("James", "Novel", "john.book@books.com", "newPassword", "123 Novel St");
        dbManager.updateUserDetails(updatedUser);
        
        User testUser = dbManager.findUser(user.getEmail()); 
        Assert.assertEquals("James", testUser.getFirstName());
        Assert.assertEquals("Novel", testUser.getLastName());
        Assert.assertEquals("123 Novel St", testUser.getAddress());
        dbManager.deleteUserByEmail(email);
    }

    @Test
    public void testDeleteUserByEmail() throws SQLException {
        User user = new User("John", "Book", "john.book@books.com", "password", "123 Book St");
        String email = "john.book@books.com";
        dbManager.addUser(user);
        
        Assert.assertTrue(dbManager.findUser(email)!=null);
        dbManager.deleteUserByEmail(email);
        Assert.assertTrue(dbManager.findUser(email)==null);
    }

    @org.junit.After
    public void tearDown() throws SQLException {
        if (connection != null) {connection.close();}
    }
}
