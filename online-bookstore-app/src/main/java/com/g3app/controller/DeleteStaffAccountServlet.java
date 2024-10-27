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

@WebServlet("/DeleteStaffAccountServlet")
public class DeleteStaffAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StaffUser staffUser = (StaffUser) session.getAttribute("user");

        if (staffUser != null) {
            int staffId = staffUser.getStaffId(); // Get the staffId of the user to delete
            
            try {
                // Open the database connection
                DBConnector connector = new DBConnector();
                Connection conn = connector.openConnection();
                DBManager dbManager = new DBManager(conn);
                
                // Perform the deletion
                dbManager.deleteStaffUser(staffId);
                
                // Close the database connection
                connector.closeConnection();
                
                // Invalidate session and redirect to the main page
                session.invalidate();
                response.sendRedirect("index.jsp");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Account deletion failed.");
            }
        } else {
            // Redirect to login if no staff user is found in session
            response.sendRedirect("staffLogin.jsp");
        }
    }
}
