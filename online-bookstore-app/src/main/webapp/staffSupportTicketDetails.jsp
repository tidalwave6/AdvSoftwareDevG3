<%@ page import="com.g3app.model.SupportTicket" %>
<%@ page import="com.g3app.model.Message" %>
<%@ page import="java.util.List" %>

<%
    SupportTicket ticket = (SupportTicket) request.getAttribute("ticket");
    String status = ticket.getStatus();  // Assuming status returns "open" or "closed"
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Staff Ticket Details - Bookstore</title>
</head>
<body>
    <jsp:include page="staff-nav-header.jsp"/>

    <main>
        <section class="ticket-details animated">
            <a href="StaffSupportDashboardServlet" class="back-arrow">
                <img src="images/back-arrow.png" alt="Back" />
            </a>
            <h1>Support Ticket Details</h1>
            
            <!-- Open Ticket Button -->
            <form action="OpenTicketServlet" method="POST" style="display: inline;">
                <input type="hidden" name="ticketId" value="<%= ticket.getTicketId() %>">
                <button type="submit" class="button" style="margin-bottom: 10px;" 
                        <%= "Open".equals(status) ? "disabled" : "" %>>
                    Open Ticket
                </button>
            </form>

            <!-- Close Ticket Button -->
            <form action="CloseTicketServlet" method="POST" style="display: inline;">
                <input type="hidden" name="ticketId" value="<%= ticket.getTicketId() %>">
                <button type="submit" class="button" style="margin-bottom: 10px;" 
                        <%= "Closed".equals(status) ? "disabled" : "" %>>
                    Close Ticket
                </button>
            </form>

            <div class="ticket-info">
                <div class="ticket-field">
                    <label for="ticketID">Ticket ID:</label>
                    <span id="ticketID"><%= ticket.getTicketId() %></span>
                </div>
                <div class="ticket-field">
                    <label for="customerName">Customer Name:</label>
                    <span id="customerName"><%= ticket.getCustomerName() %></span>
                </div>
                <div class="ticket-field">
                    <label for="email">Customer Email Address:</label>
                    <span id="email"><%= ticket.getEmail() %></span>
                </div>
                <div class="ticket-field">
                    <label for="subjectTitle">Subject Title:</label>
                    <span id="subjectTitle"><%= ticket.getSubjectTitle() %></span>
                </div>
                <div class="ticket-field">
                    <label for="type">Type:</label>
                    <span id="type"><%= ticket.getTypeOfEnquiry() %></span>
                </div>
                <div class="ticket-field">
                    <label for="status">Status:</label>
                    <span id="status"><%= ticket.getStatus() %></span>
                </div>
                <div class="ticket-field">
                    <label for="dateSubmitted">Date Submitted:</label>
                    <span id="dateSubmitted"><%= ticket.getDateSubmitted() %></span>
                </div>
                <div class="ticket-field">
                    <label for="description">Issue Description:</label>
                    <p id="description"><%= ticket.getIssueDescription() %></p>
                </div>
            </div>
                
            <div class="chat-section">
                <h2>Chat with Customer</h2>
                <div class="chat-history" id="chatHistory">
                    <% 
                    // Display the chat messages dynamically
                    List<Message> messages = (List<Message>) request.getAttribute("messages");
                    if (messages != null) {
                        for (Message msg : messages) {
                            String userType = msg.getSender(); // Assume Message has a sender attribute (e.g., "Support" or "User")
                            String messageText = msg.getMessage(); // Get the message text
                            String messageClass = userType.equals("Support") ? "you" : "otheruser"; // Determine class based on user type
                    %>
                            <div class="chat-message <%= messageClass %>">
                                <div class="message-user"><%= userType %>:</div>
                                <div class="message-text"><%= messageText %></div>
                            </div>
                    <% 
                        }
                    } 
                    %>
                </div>

                <form action="StaffMessageServlet" method="POST" id="chatForm">
                    <input type="hidden" name="ticketId" value="<%= ticket.getTicketId() %>">
                    <div class="form-group">
                        <textarea id="messageInput" name="message" rows="3" placeholder="Type your message..." required></textarea>
                    </div>
                    <div class="form-group">
                        <button type="submit" id="sendMessageButton" class="button">Send</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
</body>
</html>
