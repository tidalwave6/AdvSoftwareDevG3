package com.g3app.controller;

import com.g3app.model.StaffUser;
import com.g3app.dao.DBConnector;
import com.g3app.dao.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/UpdateStaffAccountServlet")
public class UpdateStaffAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StaffUser staffUser = (StaffUser) session.getAttribute("user");

        if (staffUser != null) {
            // Fetch updated staff details from form submission
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password"); // Optional field
            String dob = request.getParameter("dob");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String postcode = request.getParameter("postcode");
            String country = request.getParameter("country");
            String accountStatus = request.getParameter("accountStatus"); // Optional field

            try {
                // Open database connection
                DBConnector connector = new DBConnector();
                Connection conn = connector.openConnection();
                DBManager dbManager = new DBManager(conn);

                // Only update the password if it's not empty
                if (password != null && !password.trim().isEmpty()) {
                    staffUser.setPassword(password);
                }

                // Create a new StaffUser object with updated details
                StaffUser updatedStaffUser = new StaffUser(
                    firstName, lastName, email, staffUser.getPassword(), dob, phone, 
                    address, city, postcode, country, staffUser.getRole(), accountStatus, staffUser.getStaffId()
                );

                // Update the staff user details in the database
                dbManager.updateStaffUser(updatedStaffUser);

                // Close the database connection
                connector.closeConnection();

                // Update the session with the new staff details
                session.setAttribute("user", updatedStaffUser);

                // Redirect to success page
                response.sendRedirect("StaffDetailUpdate_success.jsp");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Update failed.");
            }
        } else {
            // Redirect to login if the staff user is not found in session
            response.sendRedirect("login.jsp");
        }
    }
}
