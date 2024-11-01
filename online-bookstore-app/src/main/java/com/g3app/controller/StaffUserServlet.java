package com.g3app.controller;

import com.g3app.dao.DBManager;
import com.g3app.model.StaffUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/StaffUserServlet")
public class StaffUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        // Initialize DBManager if it's not already in the session
        if (manager == null) {
            try {
                Connection conn = getDatabaseConnection(); // Implement your DB connection logic
                manager = new DBManager(conn);
                session.setAttribute("manager", manager);
            } catch (SQLException e) {
                throw new ServletException("Cannot initialize DBManager", e);
            }
        }

        String action = request.getParameter("action");

        // Load all staff users
        if (action == null || action.isEmpty() || "loadStaff".equals(action)) {
            try {
                List<StaffUser> staffUsers = manager.getAllStaffUsers();
                session.setAttribute("staffUsers", staffUsers);
                request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Cannot retrieve staff users", e);
            }
        } else if ("search".equals(action)) {
            String name = request.getParameter("name");
            try {
                List<StaffUser> staffUsers = manager.searchStaffUsers(name);
                session.setAttribute("staffUsers", staffUsers);
                request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Cannot search staff users", e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        // Initialize DBManager if it's not already in the session
        if (manager == null) {
            try {
                Connection conn = getDatabaseConnection(); // Implement your DB connection logic
                manager = new DBManager(conn);
                session.setAttribute("manager", manager);
            } catch (SQLException e) {
                throw new ServletException("Cannot initialize DBManager", e);
            }
        }

        // Get action and form data
        String action = request.getParameter("action");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");
        String country = request.getParameter("country");
        String role = request.getParameter("role");
        String accountStatus = request.getParameter("accountStatus");

        try {
            if ("update".equals(action)) {
                // Retrieve staffId and ensure it's valid
                int staffId = Integer.parseInt(request.getParameter("staffId"));

                // Retrieve the existing user details to update
                StaffUser staffUser = manager.findStaffUserById(staffId);

                if (staffUser != null) {
                    // Update user fields
                    staffUser.setFirstName(firstName);
                    staffUser.setLastName(lastName);
                    staffUser.setEmail(email);

                    // Update password only if provided
                    if (password != null && !password.trim().isEmpty()) {
                        staffUser.setPassword(password);
                    }

                    staffUser.setDob(dob);
                    staffUser.setPhone(phone);
                    staffUser.setAddress(address);
                    staffUser.setCity(city);
                    staffUser.setPostcode(postcode);
                    staffUser.setCountry(country);
                    staffUser.setRole(role);
                    staffUser.setAccountStatus(accountStatus);

                    // Update user in the database
                    manager.updateStaffUser(staffUser);

                    // Update session to reflect changes
                    List<StaffUser> staffUsers = manager.getAllStaffUsers();
                    session.setAttribute("staffUsers", staffUsers);

                    // Redirect to success page or reload the manage users page
                    response.sendRedirect("manageUsers.jsp");
                } else {
                    // Handle case where the user is not found
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Staff user not found.");
                }
            } else if ("delete".equals(action)) {
                // Handle delete action
                int staffId = Integer.parseInt(request.getParameter("staffId"));
                manager.deleteStaffUser(staffId);

                // Refresh session data
                List<StaffUser> staffUsers = manager.getAllStaffUsers();
                session.setAttribute("staffUsers", staffUsers);

                // Redirect back to manage users page
                response.sendRedirect("manageUsers.jsp");
            } else if ("add".equals(action)) {
                // Handle add new user
                StaffUser newUser = new StaffUser(firstName, lastName, email, password, dob, phone, 
                                                  address, city, postcode, country, role, accountStatus);
                manager.addStaffUser(newUser);

                // Refresh session data
                List<StaffUser> staffUsers = manager.getAllStaffUsers();
                session.setAttribute("staffUsers", staffUsers);

                // Redirect to manage users page
                response.sendRedirect("manageUsers.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException("Error managing staff users", e);
        }
    }


    // Method to return the database connection
    private Connection getDatabaseConnection() throws SQLException {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/bookstoredb";
            String username = "root";
            String password = "Passwerd1!";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Failed to create a database connection", e);
        }
    }
}
