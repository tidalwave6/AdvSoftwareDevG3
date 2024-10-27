package com.g3app.controller;

import com.g3app.model.StaffUser;
import com.g3app.dao.DBConnector;
import com.g3app.dao.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/StaffRegisterServlet")
public class StaffRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("countryCode") + " " + request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");
        String country = request.getParameter("country");

        // Default role and status
        String role = "staff";
        String accountStatus = "active";

        // Hash the password (stubbed function, replace with real hashing)
        String hashedPassword = hashPassword(password);

        DBConnector connector = null;
        Connection conn = null;

        try {
            // Create a new StaffUser object
            StaffUser newStaffUser = new StaffUser(
                firstName, lastName, email, hashedPassword, dob, 
                phone, address, city, postcode, country, role, accountStatus
            );

            // Establish a database connection
            connector = new DBConnector();
            conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);

            // Add the new staff user to the database
            dbManager.addStaffUser(newStaffUser);

            // Redirect to the login page upon successful registration
            response.sendRedirect("staffLogin.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Registration failed.");
        } finally {
            // Ensure the connection is closed
            if (connector != null) {
                try {
                    connector.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Placeholder for password hashing function
    private String hashPassword(String password) {
        // Replace this with a real hashing function (e.g., BCrypt or SHA-256)
        return password;
    }
}
