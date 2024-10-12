package com.g3app.controller;

import com.g3app.model.Message;
import com.g3app.dao.DBConnector;
import com.g3app.dao.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/StaffMessageServlet")
public class StaffMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the message and ticket ID from the request
        String messageText = request.getParameter("message");
        String ticketIdStr = request.getParameter("ticketId"); 

        // Validate parameters
        if (messageText == null || messageText.isEmpty() || ticketIdStr == null || ticketIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Message and Ticket ID are required.");
            return;
        }

        int ticketId;
        try {
            // Convert ticketId to an integer
            ticketId = Integer.parseInt(ticketIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Ticket ID format.");
            return;
        }

        String sender = "Support"; // This identifies the sender as the support staff

        // Create a new Message object
        Message message = new Message(0, ticketId, sender, messageText, null); 

        try {
            // Establish database connection
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);

            // Add message to the database
            dbManager.addMessage(message);

            // Close connection
            connector.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving message.");
            return;
        }

        // Redirect back to the staff ticket details page after successful message submission
        response.sendRedirect("StaffViewTicketDetailsServlet?ticketId=" + ticketId);
    }
}
