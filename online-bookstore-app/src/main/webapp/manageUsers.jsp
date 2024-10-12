<%@page import="java.util.List"%>
<%@page import="com.g3app.model.StaffUser"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Bookstore - Manage Staff Users</title>
</head>
<body>
    <jsp:include page="staff-nav-header.jsp"/>

    <div class="container">
        <h1>Admin Management Screen</h1>

        <!-- Search Users Form -->
        <h2>Search Users</h2>
        <form method="GET" action="StaffUserServlet">
            <input type="hidden" name="action" value="search">
            <input type="text" name="name" placeholder="Search by name">
            <button type="submit">Search</button>
        </form>

        <!-- Create New User Form -->
        <h2>New User</h2>
        <form method="POST" action="StaffUserServlet">
            <input type="hidden" name="action" value="add">
            <input type="text" name="firstName" placeholder="First Name" required>
            <input type="text" name="lastName" placeholder="Last Name" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="date" name="dob" placeholder="Date of Birth">
            <input type="text" name="phone" placeholder="Phone">
            <input type="text" name="address" placeholder="Address">
            <input type="text" name="city" placeholder="City">
            <input type="text" name="postcode" placeholder="Postcode">
            <input type="text" name="country" placeholder="Country">

            <label for="role">Role:</label>
            <select name="role" required>
                <option value="admin">Admin</option>
                <option value="staff">Staff</option>
            </select>

            <label for="accountStatus">Account Status:</label>
            <select name="accountStatus" required>
                <option value="active">Active</option>
                <option value="inactive">Inactive</option>
            </select>

            <button type="submit">Add User</button>
        </form>

        <!-- Manage Existing Staff Users Section -->
        <h2>Users</h2>
        <%
            List<StaffUser> staffUsers = (List<StaffUser>) session.getAttribute("staffUsers");
            if (staffUsers != null && !staffUsers.isEmpty()) {
                for (StaffUser staffUser : staffUsers) {
        %>
        <div class="styled-table-admin">
            <form method="POST" action="StaffUserServlet" class="form-admin">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="staffId" value="<%= staffUser.getStaffId() %>">

                <div class="form-field">
                    <label>First Name:</label>
                    <input type="text" name="firstName" value="<%= staffUser.getFirstName() %>" required>
                </div>

                <div class="form-field">
                    <label>Last Name:</label>
                    <input type="text" name="lastName" value="<%= staffUser.getLastName() %>" required>
                </div>

                <div class="form-field">
                    <label>Email:</label>
                    <input type="email" name="email" value="<%= staffUser.getEmail() %>" required>
                </div>

                <div class="form-field">
                    <label>Role:</label>
                    <select name="role" required>
                        <option value="admin" <%= "admin".equals(staffUser.getRole()) ? "selected" : "" %>>Admin</option>
                        <option value="staff" <%= "staff".equals(staffUser.getRole()) ? "selected" : "" %>>Staff</option>
                    </select>
                </div>

                <div class="form-field">
                    <label>Account Status:</label>
                    <select name="accountStatus" required>
                        <option value="active" <%= "active".equals(staffUser.getAccountStatus()) ? "selected" : "" %>>Active</option>
                        <option value="inactive" <%= "inactive".equals(staffUser.getAccountStatus()) ? "selected" : "" %>>Inactive</option>
                    </select>
                </div>

                <div class="form-field">
                    <button type="submit">Update User</button>
                </div>
            </form>

            <!-- Delete User Form -->
            <form method="POST" action="StaffUserServlet" class="form-admin">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="staffId" value="<%= staffUser.getStaffId() %>">
                <button type="submit" class="delete-button">Delete User</button>
            </form>
        </div>
        <%
                }
            } else {
        %>
        <!-- Placeholder message for no users -->
        <p>No users found.</p>
        <%
            }
        %>
    </div>
</body>
</html>
