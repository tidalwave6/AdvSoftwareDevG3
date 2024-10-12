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
        <h1>User Management</h1>
        
        <!-- Search Users Form -->
        <h2>Search Users</h2>
        <form>
            <input type="text" name="name" placeholder="Search by name" required>
            <button type="submit">Search</button>
        </form>

        <!-- Create New User Form -->
        <h2>Create New User</h2>
        <form>
            <input type="text" name="name" placeholder="Name" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>

            <label for="role">Role:</label>
            <select name="role" required>
                <option value="admin">Admin</option>
                <option value="customer">Customer</option>
                <option value="staff">Staff</option>
            </select>

            <label for="status">Status:</label>
            <select name="status" required>
                <option value="active">Active</option>
                <option value="inactive">Inactive</option>
            </select>

            <button type="submit">Add User</button>
        </form>

        <!-- Manage Existing Staff Users Section -->
        <h2>Manage Existing Staff and Customer Users</h2>
        <div class="styled-table-admin">
            <form class="form-admin">
                <div class="form-field">
                    <label>Name:</label>
                    <input type="text" name="name" value="John Smith" required>
                </div>

                <div class="form-field">
                    <label>Email:</label>
                    <input type="email" name="email" value="johnSmith@example.com" required>
                </div>

                <div class="form-field">
                    <label>Role:</label>
                    <select name="role" required>
                        <option value="admin">Admin</option>
                        <option value="customer">Customer</option>
                        <option value="staff" selected>Staff</option>
                    </select>
                </div>

                <div class="form-field">
                    <label>Status:</label>
                    <select name="status" required>
                        <option value="active" selected>Active</option>
                        <option value="inactive">Inactive</option>
                    </select>
                </div>

                <div class="form-field">
                    <button type="submit">Update User</button>
                </div>
            </form>

            <form class="form-admin">
                <button type="submit" class="delete-button">Delete User</button>
            </form>
        </div>

        <!-- Placeholder message for no users -->
        <p>No users found.</p>
    </div>
</body>
</html>
