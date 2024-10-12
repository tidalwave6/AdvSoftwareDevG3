package com.g3app.controller;

import com.g3app.dao.DBConnector;
import com.g3app.dao.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/OpenTicketServlet")
public class OpenTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketId = request.getParameter("ticketId"); // Retrieve the ticket ID from the request

        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);
            
            // Update the ticket status to "Open"
            boolean isUpdated = dbManager.updateTicketStatus(ticketId, "Open");
            connector.closeConnection();

            if (isUpdated) {
                // Redirect to the dashboard or show a success message
                response.sendRedirect("StaffSupportDashboardServlet?success=open");
            } else {
                // Handle the case where the ticket status could not be updated
                response.sendRedirect("StaffSupportDashboardServlet?error=update_failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to open the ticket.");
        }
    }
}
