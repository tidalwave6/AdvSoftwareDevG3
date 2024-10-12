package com.g3app.dao;

import com.g3app.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Connection conn;  // Ensure this is properly initialized
    public Statement st;

    public DBManager(Connection conn) throws SQLException {
        this.conn = conn; // Assign the passed connection to this.conn
        if (this.conn != null) {
            st = conn.createStatement();
        } else {
            throw new SQLException("Database connection is null!");
        }
    }

    // find customer user
    public User findUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return new User(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("dob"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("city"),
                rs.getString("postcode"),
                rs.getString("country")
            );
        }
        return null; // No user found
    }

    // add customer user
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (firstName, lastName, email, password, dob, phone, address, city, postcode, country) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, user.getFirstName());
        pstmt.setString(2, user.getLastName());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPassword());
        pstmt.setString(5, user.getDob());
        pstmt.setString(6, user.getPhone());
        pstmt.setString(7, user.getAddress());
        pstmt.setString(8, user.getCity());
        pstmt.setString(9, user.getPostcode());
        pstmt.setString(10, user.getCountry());
        pstmt.executeUpdate();
    }
   
    // Add staff user (without manually providing staffId)
    public void addStaffUser(StaffUser staffUser) throws SQLException {
        String query = "INSERT INTO staffusers (firstName, lastName, email, password, dob, phone, address, city, postcode, country, role, accountStatus) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, staffUser.getFirstName());
        pstmt.setString(2, staffUser.getLastName());
        pstmt.setString(3, staffUser.getEmail());
        pstmt.setString(4, staffUser.getPassword());
        pstmt.setString(5, staffUser.getDob());
        pstmt.setString(6, staffUser.getPhone());
        pstmt.setString(7, staffUser.getAddress());
        pstmt.setString(8, staffUser.getCity());
        pstmt.setString(9, staffUser.getPostcode());
        pstmt.setString(10, staffUser.getCountry());
        pstmt.setString(11, staffUser.getRole());
        pstmt.setString(12, staffUser.getAccountStatus());
        pstmt.executeUpdate();
    }
    public StaffUser findStaffUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM staffusers WHERE email = ? AND password = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int staffId = rs.getInt("staffId");
            return new StaffUser(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("dob"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("city"),
                rs.getString("postcode"),
                rs.getString("country"),
                rs.getString("role"),
                rs.getString("accountStatus"),
                staffId
            );
        }
        return null;
    }

    // Get all staff users
    public List<StaffUser> getAllStaffUsers() throws SQLException {
        List<StaffUser> staffUsers = new ArrayList<>();
        String query = "SELECT * FROM staffusers";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            StaffUser staffUser = new StaffUser(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("dob"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("city"),
                rs.getString("postcode"),
                rs.getString("country"),
                rs.getString("role"),
                rs.getString("accountStatus"),
                rs.getInt("staffId")
            );
            staffUsers.add(staffUser);
        }
        return staffUsers;
    }

    // Delete staff user
    public void deleteStaffUser(int staffId) throws SQLException {
        String query = "DELETE FROM staffusers WHERE staffId = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setInt(1, staffId);
        pstmt.executeUpdate();
    }

    // Update staff user
    public void updateStaffUser(StaffUser staffUser) throws SQLException {
        String query = "UPDATE staffusers SET firstName = ?, email = ?, password = ?, role = ?, accountStatus = ? WHERE staffId = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, staffUser.getFirstName());
        pstmt.setString(2, staffUser.getEmail());
        pstmt.setString(3, staffUser.getPassword());
        pstmt.setString(4, staffUser.getRole());
        pstmt.setString(5, staffUser.getAccountStatus());
        pstmt.setInt(6, staffUser.getStaffId());
        pstmt.executeUpdate();
    }

    // Search staff users by name
    public List<StaffUser> searchStaffUsers(String name) throws SQLException {
        List<StaffUser> staffUsers = new ArrayList<>();
        String query = "SELECT * FROM staffusers WHERE firstName LIKE ? OR lastName LIKE ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, "%" + name + "%");
        pstmt.setString(2, "%" + name + "%");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            StaffUser staffUser = new StaffUser(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("dob"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("city"),
                rs.getString("postcode"),
                rs.getString("country"),
                rs.getString("role"),
                rs.getString("accountStatus"),
                rs.getInt("staffId")
            );
            staffUsers.add(staffUser);
        }
        return staffUsers;
    }

        
    
    public boolean deleteUserByEmail(String email) throws SQLException {
    String query = "DELETE FROM users WHERE email = ?";
    PreparedStatement pstmt = st.getConnection().prepareStatement(query);

    try {
        pstmt.setString(1, email);
        int rowsAffected = pstmt.executeUpdate();

        return rowsAffected > 0; // returns true assuming at least one row was deleted
    } finally {
        if (pstmt != null) {
            pstmt.close(); // close statement to release resources
        }
    }
}
    public boolean deleteBookByTitle(String title) throws SQLException {
    String query = "DELETE FROM books WHERE title = ?";
    PreparedStatement pstmt = st.getConnection().prepareStatement(query);

    try {
         pstmt.setString(1, title); // Set the title parameter

        // Execute the update
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0; // Returns true if a book was deleted
    } finally {
        // Close the PreparedStatement if it was initialized
        if (pstmt != null) {
            pstmt.close();
        }
    }
}

    
  public boolean updateUserDetails(String oldEmail, String firstName, String lastName, String email, String password, String dob, String phone, String address, String city, String postcode, String country) throws SQLException {
    String query = "UPDATE users SET firstName = ?, lastName = ?, email = ?, password = ?, dob = ?, phone = ?, address = ?, city = ?, postcode = ?, country = ? WHERE email = ?";
    PreparedStatement pstmt = st.getConnection().prepareStatement(query);
    
    try{
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, email);
        pstmt.setString(4, password);
        pstmt.setString(5, dob);
        pstmt.setString(6, phone);
        pstmt.setString(7, address);
        pstmt.setString(8, city);
        pstmt.setString(9, postcode);
        pstmt.setString(10, country);
        pstmt.setString(11, oldEmail); // identify user by old email
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0; // returns true if one row was updated
    }
    finally {
        if (pstmt != null) {
            pstmt.close(); // close statement to release resources
        }
    }
    }
    
public void addBook(Book book) throws SQLException {
    String query = "INSERT INTO books (title, author, price, publishedDate, description, imgUrl, genre, medium) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement pstmt = st.getConnection().prepareStatement(query);
    
    try {
        pstmt.setString(1, book.getTitle());
        pstmt.setString(2, book.getAuthor());
        pstmt.setDouble(3, book.getPrice());
        pstmt.setString(4, book.getPublishedDate());
        pstmt.setString(5, book.getDescription());
        pstmt.setString(6, book.getImgUrl());
        pstmt.setString(7, book.getGenre());
        pstmt.setString(8, book.getMedium());

        pstmt.executeUpdate(); // Execute the insert statement
    } finally {
        if (pstmt != null) {
            pstmt.close(); // Close statement to release resources
        }
    }
}

    
    public ArrayList<Book> getAllBooks() throws SQLException {
    ArrayList<Book> books = new ArrayList<>();
    String query = "SELECT * FROM books"; // Ensure your table structure includes genre and medium
    PreparedStatement pstmt = st.getConnection().prepareStatement(query);
    ResultSet rs = pstmt.executeQuery();

    while (rs.next()) {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        double price = rs.getDouble("price");
        String publishedDate = rs.getString("publishedDate");
        String description = rs.getString("description");
        String imgUrl = rs.getString("imgUrl");
        String genre = rs.getString("genre"); // New line to get genre
        String medium = rs.getString("medium"); // Change the variable name to medium

        Book book = new Book(id, title, author, price, publishedDate, description, imgUrl, genre, medium); // Use medium
        books.add(book);
    }
    return books;
}

    
    public Book getBookById(int id) throws SQLException {
    Book book = null;
    String query = "SELECT * FROM books WHERE id = ?"; // Use parameterized query for security
    PreparedStatement pstmt = st.getConnection().prepareStatement(query);
    pstmt.setInt(1, id); // Set the ID parameter
    ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // Create a book object from the result set
            book = new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getDouble("price"),
                rs.getString("publishedDate"),
                rs.getString("description"),
                rs.getString("imgUrl"),
                rs.getString("genre"),
                rs.getString("medium")
            );
        } else {
            System.out.println("No book found with ID: " + id); // Debug message
        }
    
    return book; // Return the book object or null if not found
}
    
    public void createSupportTicket(SupportTicket ticket) throws SQLException {
        String query = "INSERT INTO support_tickets (customer_name, email, subject_title, type_of_enquiry, issue_description, status, date_submitted) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, ticket.getCustomerName());
        pstmt.setString(2, ticket.getEmail());
        pstmt.setString(3, ticket.getSubjectTitle());
        pstmt.setString(4, ticket.getTypeOfEnquiry());
        pstmt.setString(5, ticket.getIssueDescription());
        pstmt.setString(6, ticket.getStatus());
        pstmt.setDate(7, new java.sql.Date(ticket.getDateSubmitted().getTime()));
        pstmt.executeUpdate();
    }
    
public List<SupportTicket> getAllSupportTickets() throws SQLException {
    String query = "SELECT * FROM support_tickets";
    ResultSet rs = st.executeQuery(query);
    List<SupportTicket> tickets = new ArrayList<>();
    while (rs.next()) {
        // Use the constructor with parameters
        SupportTicket ticket = new SupportTicket(
            rs.getInt("ticket_id"),
            rs.getString("customer_name"),
            rs.getString("email"),
            rs.getString("subject_title"),
            rs.getString("type_of_enquiry"),
            rs.getString("issue_description"),
            rs.getString("status"),
            rs.getDate("date_submitted") // This can be converted to Date if necessary
        );
        tickets.add(ticket);
    }
    return tickets;
}

public SupportTicket getSupportTicketById(int ticketId) throws SQLException {
    String query = "SELECT * FROM support_tickets WHERE ticket_id = ?";
    PreparedStatement pstmt = st.getConnection().prepareStatement(query);
    pstmt.setInt(1, ticketId);
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
        // Use the constructor with parameters
        return new SupportTicket(
            rs.getInt("ticket_id"),
            rs.getString("customer_name"),
            rs.getString("email"),
            rs.getString("subject_title"),
            rs.getString("type_of_enquiry"),
            rs.getString("issue_description"),
            rs.getString("status"),
            rs.getDate("date_submitted")
        );
    }
    return null; // No ticket found
}

    public List<SupportTicket> getTicketsByEmail(String email) throws SQLException {
        String query = "SELECT * FROM support_tickets WHERE email = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, email);

        ResultSet rs = pstmt.executeQuery();

        List<SupportTicket> tickets = new ArrayList<>();

        while (rs.next()) {
            // Use the constructor with parameters
            SupportTicket ticket = new SupportTicket(
                rs.getInt("ticket_id"),
                rs.getString("customer_name"),
                rs.getString("email"),
                rs.getString("subject_title"),
                rs.getString("type_of_enquiry"),
                rs.getString("issue_description"),
                rs.getString("status"),
                rs.getDate("date_submitted")
            );
            tickets.add(ticket);
        }
        return tickets;
    }
    
    public boolean updateTicketStatus(String ticketId, String status) throws SQLException {
        String query = "UPDATE support_tickets SET status = ? WHERE ticket_id = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);

        pstmt.setString(1, status);
        pstmt.setInt(2, Integer.parseInt(ticketId)); // Convert ticketId to integer if needed

        int rowsAffected = pstmt.executeUpdate(); // Execute the update

        return rowsAffected > 0; // Return true if the update was successful
    }

    public void addMessage(Message message) throws SQLException {
        String query = "INSERT INTO ticket_messages (ticket_id, sender, message) VALUES (?, ?, ?)";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setInt(1, message.getTicketId());
        pstmt.setString(2, message.getSender());
        pstmt.setString(3, message.getMessage());
        pstmt.executeUpdate();
}

    public List<Message> getMessagesByTicketId(int ticketId) throws SQLException {
        String query = "SELECT * FROM ticket_messages WHERE ticket_id = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setInt(1, ticketId);
        ResultSet rs = pstmt.executeQuery();

        List<Message> messages = new ArrayList<>();
        while (rs.next()) {
            Message message = new Message(
                rs.getInt("message_id"),
                rs.getInt("ticket_id"),
                rs.getString("sender"),
                rs.getString("message"),
                rs.getTimestamp("timestamp")
            );
            messages.add(message);
        }
        return messages;
    }
    
    public boolean deleteTicketById(int ticketId) throws SQLException {
        String query = "DELETE FROM support_tickets WHERE ticket_id = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setInt(1, ticketId);

        int rowsAffected = pstmt.executeUpdate(); // Execute the deletion

        return rowsAffected > 0; // Return true if the ticket was deleted successfully
    }

    public List<SupportTicket> getOpenedSupportTickets() throws SQLException {
        String query = "SELECT * FROM support_tickets WHERE status = 'Open'";
        List<SupportTicket> openedTickets = new ArrayList<>();
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            SupportTicket ticket = new SupportTicket(
                rs.getInt("ticket_id"),
                rs.getString("customer_name"),
                rs.getString("email"),
                rs.getString("subject_title"),
                rs.getString("type_of_enquiry"),
                rs.getString("issue_description"),
                rs.getString("status"),
                rs.getDate("date_submitted")
            );
            openedTickets.add(ticket);
        }
        return openedTickets;
    }

    public List<SupportTicket> getClosedSupportTickets() throws SQLException {
        String query = "SELECT * FROM support_tickets WHERE status = 'Closed'";
        List<SupportTicket> closedTickets = new ArrayList<>();
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            SupportTicket ticket = new SupportTicket(
                rs.getInt("ticket_id"),
                rs.getString("customer_name"),
                rs.getString("email"),
                rs.getString("subject_title"),
                rs.getString("type_of_enquiry"),
                rs.getString("issue_description"),
                rs.getString("status"),
                rs.getDate("date_submitted")
            );
            closedTickets.add(ticket);
        }
        return closedTickets;
    }
    
    //SHIPMENTS CRUD
    
    public void createShipment(Shipment shipment) throws SQLException{
    String query = "INSERT INTO shipments (ShipmentID,ShipmentDate, ShipmentProgress, ShipmentStatus)" +
                       "VALUES (?,?, ?, ?)";
        PreparedStatement statement = st.getConnection().prepareStatement(query);
        statement.setInt(1, shipment.getID());
        statement.setString(2, shipment.getDate());
        statement.setString(3, shipment.getProgress());
        statement.setString(4, shipment.getStatus());
        statement.executeUpdate();
    }
    public boolean readShipment() throws SQLException{
        String query="SELECT * FROM shipments";
        PreparedStatement statement = st.getConnection().prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        int shipmentNum = 0;
        while (rs.next()) {
            shipmentNum++;
        }
        return shipmentNum > 0;
    }
    
    public void updateShipment(Shipment shipment) throws SQLException{
        String query = "UPDATE shipments SET ShipmentDate = ?, ShipmentProgress = ?, ShipmentStatus = ? WHERE ShipmentID = ?";
        PreparedStatement statement = st.getConnection().prepareStatement(query);
        statement.setString(1, shipment.getDate());
        statement.setString(2, shipment.getProgress());
        statement.setString(3, shipment.getStatus());
        statement.setInt(4, shipment.getID());
        statement.executeUpdate();
    }
    
    public void deleteShipment(Shipment shipment) throws SQLException{
        String query = "DELETE FROM shipments WHERE ShipmentID = ?";
        PreparedStatement statement = st.getConnection().prepareStatement(query);
        statement.setInt(1, shipment.getID());
        statement.executeUpdate();
    }
    
    public Shipment getShipmentByID(int id) throws SQLException{
        String query = "SELECT * FROM shipments WHERE ShipmentID = ?";
        PreparedStatement statement = st.getConnection().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            Shipment shipment = new Shipment();
            shipment.setID(rs.getInt("ShipmentID"));
            shipment.setDate(rs.getString("ShipmentDate"));
            shipment.setProgress(rs.getString("ShipmentProgress"));
            shipment.setStatus(rs.getString("ShipmentStatus"));
            return shipment;
            }
        return null;
    }
public void addPaymentMethod(Payment payment) throws SQLException {
        String query = "INSERT INTO payments (cardNumber, expiryDate, cardHolderName) VALUES (?, ?, ?)";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, payment.getCardNumber());
        pstmt.setString(2, payment.getExpiryDate());
        pstmt.setString(3, payment.getCardHolderName());
        pstmt.executeUpdate();
    }

    public void updatePaymentMethod(Payment payment) throws SQLException {
        String query = "UPDATE payments SET cardNumber = ?, expiryDate = ?, cardHolderName = ? WHERE paymentId = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setString(1, payment.getCardNumber());
        pstmt.setString(2, payment.getExpiryDate());
        pstmt.setString(3, payment.getCardHolderName());
        pstmt.setInt(4, payment.getPaymentId());
        pstmt.executeUpdate();
    }

    public void deletePaymentMethod(int paymentId) throws SQLException {
        String query = "DELETE FROM payments WHERE paymentId = ?";
        PreparedStatement pstmt = st.getConnection().prepareStatement(query);
        pstmt.setInt(1, paymentId);
        pstmt.executeUpdate();
}
}
