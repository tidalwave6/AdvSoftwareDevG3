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

            <div class="tabs">
                <div class="tab active" data-target="openedTickets">Opened Tickets</div>
                <div class="tab" data-target="closedTickets">Closed Tickets</div>
            </div>
            
            <!-- OPENED TICKETS TAB -->
            <div id="openedTickets" class="tab-content active">
                <h2>Opened Support Tickets</h2>
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
                    <tbody>
                        <%
                            // Retrieve the list of opened support tickets from the request attribute
                            List<SupportTicket> openedTickets = (List<SupportTicket>) request.getAttribute("openedTickets");
                            if (openedTickets != null && !openedTickets.isEmpty()) {
                                for (SupportTicket ticket : openedTickets) {
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
                            <td colspan="8">No opened tickets found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            
            <!-- CLOSED TICKETS TAB -->
            <div id="closedTickets" class="tab-content">
                <h2>Closed Support Tickets</h2>
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
                    <tbody>
                        <%
                            // Retrieve the list of closed support tickets from the request attribute
                            List<SupportTicket> closedTickets = (List<SupportTicket>) request.getAttribute("closedTickets");
                            if (closedTickets != null && !closedTickets.isEmpty()) {
                                for (SupportTicket ticket : closedTickets) {
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
                            <td colspan="8">No closed tickets found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </section>
    </main>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            // Tab functionality
            const tabs = document.querySelectorAll('.tab');
            const tabContents = document.querySelectorAll('.tab-content');

            tabs.forEach(tab => {
                tab.addEventListener('click', () => {
                    const targetId = tab.getAttribute('data-target');

                    tabs.forEach(t => t.classList.remove('active'));
                    tabContents.forEach(content => content.classList.remove('active'));

                    tab.classList.add('active');
                    document.getElementById(targetId).classList.add('active');
                });
            });
        });
    </script>

    <jsp:include page="footer.jsp"/>
</body>
</html>
