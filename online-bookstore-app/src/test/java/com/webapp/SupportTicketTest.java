package com.webapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.g3app.dao.DBManager;
import com.g3app.model.SupportTicket;
import com.g3app.model.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;

public class SupportTicketTest {

    private SupportTicket ticket;
    private DBManager dbManager;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        // create the support ticket object with some data before each test
        ticket = new SupportTicket(0, "Jane Doe", "jane.doe@company.com", 
                                   "Login Issues", "Technical", 
                                   "Unable to log into my account", 
                                   "Open", new java.util.Date());

        // create the database connection and DBManager
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "root");
        dbManager = new DBManager(connection);

        // Ensure the ticket does not exist before adding (cleanup)
        List<SupportTicket> tickets = dbManager.getTicketsByEmail(ticket.getEmail());
        for (SupportTicket existingTicket : tickets) {
            dbManager.deleteTicketById(existingTicket.getTicketId()); // Assuming you have a method for deleting tickets
        }

        // Add the new support ticket to the database
        dbManager.createSupportTicket(ticket);
    }

    // Verify that the support ticket is created correctly
    @Test
    public void testCreateSupportTicket() throws SQLException {
        List<SupportTicket> tickets = dbManager.getTicketsByEmail(ticket.getEmail());
        Assert.assertNotNull(tickets);
        Assert.assertTrue(!tickets.isEmpty());
        SupportTicket retrievedTicket = tickets.get(0);

        // Validate the details of the ticket
        Assert.assertEquals("Jane Doe", retrievedTicket.getCustomerName());
        Assert.assertEquals("jane.doe@company.com", retrievedTicket.getEmail());
        Assert.assertEquals("Login Issues", retrievedTicket.getSubjectTitle());
        Assert.assertEquals("Technical", retrievedTicket.getTypeOfEnquiry());
        Assert.assertEquals("Unable to log into my account", retrievedTicket.getIssueDescription());
        Assert.assertEquals("Open", retrievedTicket.getStatus());
    }

    // Test retrieval of a support ticket by ticket ID
    @Test
    public void testGetSupportTicketById() throws SQLException {
        List<SupportTicket> tickets = dbManager.getTicketsByEmail(ticket.getEmail());
        Assert.assertTrue(!tickets.isEmpty());
        SupportTicket retrievedTicket = tickets.get(0);

        SupportTicket fetchedTicket = dbManager.getSupportTicketById(retrievedTicket.getTicketId());
        Assert.assertNotNull(fetchedTicket);
        Assert.assertEquals(retrievedTicket.getTicketId(), fetchedTicket.getTicketId());
        Assert.assertEquals("Jane Doe", fetchedTicket.getCustomerName());
    }
    
    @Test
    public void testAddMessage() throws SQLException {
        // Retrieve the ticket ID from the database
        List<SupportTicket> tickets = dbManager.getTicketsByEmail(ticket.getEmail());
        Assert.assertTrue(!tickets.isEmpty());
        SupportTicket retrievedTicket = tickets.get(0);

        // Create a new message for this support ticket
        Message message = new Message(0, retrievedTicket.getTicketId(), "Jane Doe", "I need help with my login issue", new Timestamp(new java.util.Date().getTime()));

        // Add the message to the database
        dbManager.addMessage(message);

        // Retrieve the messages for this ticket from the database
        List<Message> messages = dbManager.getMessagesByTicketId(retrievedTicket.getTicketId());

        // Assert that the message was added correctly
        Assert.assertNotNull(messages);
        Assert.assertTrue(!messages.isEmpty());
        Message retrievedMessage = messages.get(0);

        // Validate message details
        Assert.assertEquals(retrievedTicket.getTicketId(), retrievedMessage.getTicketId());
        Assert.assertEquals("Jane Doe", retrievedMessage.getSender());
        Assert.assertEquals("I need help with my login issue", retrievedMessage.getMessage());
    }
    
    // Clean up the database after each test
    @org.junit.After
    public void tearDown() throws SQLException {
        // Remove the support ticket to avoid conflicts in future tests
        List<SupportTicket> tickets = dbManager.getTicketsByEmail(ticket.getEmail());
        for (SupportTicket existingTicket : tickets) {
            dbManager.deleteTicketById(existingTicket.getTicketId()); // Assuming you have this method
        }

        // Close the database connection
        if (connection != null) {
            connection.close();
        }
    }
}
