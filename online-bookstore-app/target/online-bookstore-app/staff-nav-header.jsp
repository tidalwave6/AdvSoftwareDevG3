<%@ page import="com.g3app.model.StaffUser" %>
<html>
<header>
    <nav>
        <div class="logo">
            <img src="images/book-logo.png" alt="Bookstore Logo" class="logo-img">
            Bookstore
        </div>
        <ul class="nav-links">
            <li><a href="manageUsers.jsp">Manage Users</a></li>
            <li><a href="staffCatalogue.jsp">Manage Catalogue</a></li>
            <li><a href="manageOrders.jsp">Manage Orders</a></li>
            <li><a href="managePayments.jsp">Manage Payments</a></li>
            <li><a href="shipment_table.jsp">Manage Shipping</a></li>
            <li><a href="StaffSupportDashboardServlet">Support Tickets</a></li>
        </ul>

        <div class="user-actions">
            <%
                StaffUser staffUser = (StaffUser) request.getSession().getAttribute("user");
                if (staffUser != null) {
                    // If the user is logged in, display My Account and Log Out
                    String email = staffUser.getEmail();
            %>
                <a href="mystaffdetails.jsp">My Account (<%= email %>)</a>
                <a href="LogoutServlet">Log Out</a>
            <%
                } else {
                    // Optionally, you can include a login link here if needed
                    // <a href="login.jsp">Login</a>
                }
            %>
        </div>
    </nav>
</header>
</html>