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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("countryCode") + request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");
        String country = request.getParameter("country");
        String role = request.getParameter("role"); // New field for role
        String accountStatus = request.getParameter("accountStatus"); // New field for account status

        // Hash the password for security before saving
        String hashedPassword = hashPassword(password);

        DBConnector connector = null;
        Connection conn = null;
        try {
            StaffUser newStaffUser = new StaffUser(firstName, lastName, email, hashedPassword, dob, phone, address, city, postcode, country, role, accountStatus);

            // Manually manage the DB connection
            connector = new DBConnector();
            conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);
            dbManager.addStaffUser(newStaffUser); // Call the method for staff
            response.sendRedirect("staffLogin.jsp"); // Redirect to login after successful registration
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Registration failed.");
        } finally {
            // Ensure that the connection is closed properly
            if (connector != null) {
                try {
                    connector.closeConnection(); // Close the connection manually
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Placeholder for a password hashing function
    private String hashPassword(String password) {
        return password;
    }
}
