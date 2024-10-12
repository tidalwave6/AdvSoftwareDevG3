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

        // Get the action and parameters from the request
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
        String accountStatus = request.getParameter("accountStatus") == null ? "active" : request.getParameter("accountStatus");

        // Handle add, update, and delete actions
        try {
            if ("delete".equals(action)) {
                int staffId = Integer.parseInt(request.getParameter("staffId"));
                manager.deleteStaffUser(staffId);
            } else {
                // Create a new StaffUser object without the staffId (auto-assigned by the database)
                StaffUser staffUser = new StaffUser(firstName, lastName, email, password, dob, phone, address, city, postcode, country, role, accountStatus);

                if ("add".equals(action)) {
                    manager.addStaffUser(staffUser);
                } else if ("update".equals(action)) {
                    int staffId = Integer.parseInt(request.getParameter("staffId")); // Only required for update
                    staffUser.setStaffId(staffId);
                    manager.updateStaffUser(staffUser);
                }
            }

            // Reload the staff users list and forward to JSP
            List<StaffUser> staffUsers = manager.getAllStaffUsers();
            session.setAttribute("staffUsers", staffUsers);
            request.getRequestDispatcher("manageUsers.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error managing staff users", e);
        }
    }

    // Method to return the database connection
    private Connection getDatabaseConnection() throws SQLException {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/bookstoredb";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Failed to create a database connection", e);
        }
    }
}
