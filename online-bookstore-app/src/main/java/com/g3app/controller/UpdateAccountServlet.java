package com.g3app.controller;

import com.g3app.model.User;
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

@WebServlet("/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user != null) {
        // Fetch updated user details from form submission
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

        try {
            // Open database connection
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);

            // Only update password if it's not empty
            if (password != null && !password.trim().isEmpty()) {
                user.setPassword(password); // Update the password in the user object
            }

            // Update the user details (send password only if it's updated)
            boolean success = dbManager.updateUserDetails(
                user.getEmail(), // Identify the user by email
                firstName, lastName, email, user.getPassword(), dob, phone, address, city, postcode, country
            );

            // Close database connection
            connector.closeConnection();

            if (success) {
                // Update session with new user details
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setDob(dob);
                user.setPhone(phone);
                user.setAddress(address);
                user.setCity(city);
                user.setPostcode(postcode);
                user.setCountry(country);
                session.setAttribute("user", user);

                // Redirect to success page
                response.sendRedirect("detailUpdate_success.jsp");
            } else {
                // Redirect to account details page with error message
                response.sendRedirect("mydetails.jsp?status=error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Update failed.");
        }
    } else {
        // Redirect to login if user is not found in session
        response.sendRedirect("login.jsp");
    }
    }
}
