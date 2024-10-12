<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.g3app.model.SupportTicket" %>
<%@ page import="com.g3app.model.Message" %>
<%@ page import="java.util.List" %>

<%
    SupportTicket ticket = (SupportTicket) request.getAttribute("ticket");
    List<Message> messages = (List<Message>) request.getAttribute("messages"); // Retrieve messages from request
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>View Ticket - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="ticket-details animated">
            <a href="UserSupportDashboardServlet" class="back-arrow">
                <img src="images/back-arrow.png" alt="Back" />
            </a>
            <h1>Support Ticket Details</h1>
            <div class="ticket-info">
                <div class="ticket-field">
                    <label for="ticketID">Ticket ID:</label>
                    <span id="ticketID"><%= ticket.getTicketId() %></span>
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
                <h2>Chat with Support</h2>
                <div class="chat-history" id="chatHistory">
                    <% 
                    // Display the chat messages dynamically
                    if (messages != null) {
                        for (Message msg : messages) {
                            String userType = msg.getSender(); // Assume Message has a sender attribute (e.g., "Support" or "User")
                            String messageText = msg.getMessage(); // Get the message text
                            String messageClass = userType.equals("Support") ? "otheruser" : "you"; // Determine class based on user type
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

                <form id="chatForm" action="MessageServlet" method="POST">
                    <input type="hidden" name="ticketId" value="<%= ticket.getTicketId() %>">
                    <div class="form-group">
                        <textarea id="message" name="message" rows="3" placeholder="Type your message..." required></textarea>
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
