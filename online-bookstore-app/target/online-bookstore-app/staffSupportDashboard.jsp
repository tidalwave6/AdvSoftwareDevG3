<%@ page import="com.g3app.model.SupportTicket" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Staff Support Tickets - Bookstore</title>
</head>
<body>
    <jsp:include page="staff-nav-header.jsp"/>

    <main>
        <section class="staff-tickets-section animated">
            <h1>Support Tickets Management</h1>
            <p>Welcome to the staff dashboard. Here you can view and manage all customer support tickets.</p>

            <h2>All Support Tickets</h2>
            <table>
                <thead>
                    <tr>
                        <th>Ticket ID</th>
                        <th>Customer Name</th>
                        <th>Customer Email Address</th>
                        <th>Subject Title</th>
                        <th>Type</th>
                        <th>Status</th>
                        <th>Date Submitted</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <%
                        // Retrieve the list of support tickets from the request attribute
                        List<SupportTicket> tickets = (List<SupportTicket>) request.getAttribute("allTickets");
                        if (tickets != null && !tickets.isEmpty()) {
                            for (SupportTicket ticket : tickets) {
                    %>
                    <tr>
                        <td><%= ticket.getTicketId() %></td>
                        <td><%= ticket.getCustomerName() %></td>
                        <td><%= ticket.getEmail() %></td>
                        <td><%= ticket.getSubjectTitle() %></td>
                        <td><%= ticket.getTypeOfEnquiry() %></td>
                        <td><%= ticket.getStatus() %></td>
                        <td><%= ticket.getDateSubmitted() %></td>
                        <td>
                            <a href="StaffViewTicketDetailsServlet?ticketId=<%= ticket.getTicketId() %>" class="button view-ticket">View</a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="7">No support tickets found.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </section>
    </main>
</body>
</html>
