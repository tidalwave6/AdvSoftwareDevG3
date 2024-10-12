package com.webapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.g3app.dao.DBManager;
import com.g3app.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookTest {

    private Book book;
    private DBManager dbManager;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        // create book object with some data before each test
        book = new Book("book", "author", 19.99, "2024-01-01", "description", "http://image.url", "Fiction", "Paperback");

        // create the database connection and DBManager
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "root");
        dbManager = new DBManager(connection);

        // ensure the book does not exist before adding
        dbManager.deleteBookByTitle(book.getTitle()); // Clean up any existing books
        dbManager.addBook(book); // Add the new book to the database
    }

    // verify the book is created correctly
    @Test
    public void testBookCreation() {
        Assert.assertNotNull(book);
        Assert.assertEquals("book", book.getTitle());
        Assert.assertEquals("author", book.getAuthor());
        Assert.assertEquals(19.99, book.getPrice(), 0.01);
    }

    @Test
    public void testDeleteBookByTitle() throws SQLException {
        // delete the book using the title
        boolean result = dbManager.deleteBookByTitle(book.getTitle());

        // ensure deletion was successful
        Assert.assertTrue("Book should be deleted successfully.", result);

        // check that the book no longer exists in the database
        Book deletedBook = dbManager.getBookById(book.getId());
        Assert.assertNull("Book should not be found after deletion.", deletedBook);
    }

    // close the database connection after tests
    @org.junit.After
    public void tearDown() throws SQLException {
        // clean up after tests to avoid conflicts in future tests
        dbManager.deleteBookByTitle(book.getTitle());

        // close the connection
        if (connection != null) {
            connection.close();
        }
    }
}
