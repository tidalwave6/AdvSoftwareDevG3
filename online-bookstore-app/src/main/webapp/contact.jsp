<%@ page import="com.g3app.model.SupportTicket" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Contact Support - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="contact-section animated">
            <h1>Contact Support</h1>
            <p>Welcome to the support dashboard. Here you can manage your existing support tickets and submit new requests. 
                Use the tabs below to navigate between viewing your tickets and creating a new support request.</p>

            <div class="tabs">
                <div class="tab active" data-target="dashboard">Dashboard</div>
                <div class="tab" data-target="submitTicket">Create New Ticket</div>

            </div>
            
            <!-- DASHBOARD TAB -->
            <div id="dashboard" class="tab-content active">
                <h2>Your Support Tickets</h2>
                <p>Here you can view and manage all your support tickets. Click on a ticket to view its details.</p>

                <table>
                    <thead>
                        <tr>
                            <th>Ticket ID</th>
                            <th>Subject Title</th>
                            <th>Type</th>
                            <th>Status</th>
                            <th>Date Submitted</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<SupportTicket> tickets = (List<SupportTicket>) request.getAttribute("tickets");
                            if (tickets != null && !tickets.isEmpty()) {
                                for (SupportTicket ticket : tickets) {
                                    // Determine the class based on the ticket status
                                    String statusClass = ticket.getStatus().equals("Closed") ? "closed-ticket" : "opened-ticket";
                        %>
                        <tr class="<%= statusClass %>">
                            <td><%= ticket.getTicketId() %></td>
                            <td><%= ticket.getSubjectTitle() %></td>
                            <td><%= ticket.getTypeOfEnquiry() %></td>
                            <td><%= ticket.getStatus() %></td>
                            <td><%= ticket.getDateSubmitted() %></td>
                            <td><a href="ViewTicketServlet?ticketId=<%= ticket.getTicketId() %>" class="button">View</a></td>
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="6">No support tickets found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            
            <!-- CREATE NEW TICKET TAB -->
            <div id="submitTicket" class="tab-content">
                <h2>Submit Your Support Request</h2>
                <form id="submitTicketForm" action="SubmitTicketServlet" method="post">
                    <div class="form-group">
                        <label for="customer_name">Full Name:</label>
                        <input type="text" id="customer_name" name="customer_name" placeholder="Enter your full name" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email Address:</label>
                        <input type="email" id="email" name="email" placeholder="Enter your email address" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="subject_title">Subject Title:</label>
                        <input type="text" id="subject_title" name="subject_title" placeholder="Enter a brief title for your issue" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="type_of_enquiry">Type of Enquiry:</label>
                        <select id="type_of_enquiry" name="type_of_enquiry" required>
                            <option value="">Select an option</option>
                            <option value="General">General Inquiry</option>
                            <option value="Technical">Technical Support</option>
                            <option value="Billing">Billing Issues</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="issue_description">Issue Description:</label>
                        <textarea id="issue_description" name="issue_description" rows="5" placeholder="Describe your issue in detail" required></textarea>
                    </div>
                    
                    <div class="form-group">
                        <button type="submit" id="submitTicketButton" disabled>Submit Ticket</button>
                    </div>
                </form>
            </div>
        </section>
    </main>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            function validateForm(formId, buttonId) {
                const form = document.getElementById(formId);
                const submitButton = document.getElementById(buttonId);
                const inputs = form.querySelectorAll('input[required], textarea[required], select[required]');
                
                function updateButtonState() {
                    let allValid = true;

                    inputs.forEach(input => {
                        if (input.type === 'select-one') {
                            // Check if select field has a value
                            if (!input.value.trim()) {
                                allValid = false;
                            }
                        } else {
                            // Check if input field is filled
                            if (!input.value.trim()) {
                                allValid = false;
                            }
                        }
                    });

                    submitButton.disabled = !allValid;
                    submitButton.style.backgroundColor = allValid ? '#4CAF50' : '#ccc';
                }

                form.addEventListener('input', updateButtonState);
                form.addEventListener('change', updateButtonState); // Add change event listener for select fields
                updateButtonState(); // Initial check
            }

            // Validate the submit ticket form
            validateForm('submitTicketForm', 'submitTicketButton');

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
    </script><jsp:include page="footer.jsp"/>
</body>
</html>
