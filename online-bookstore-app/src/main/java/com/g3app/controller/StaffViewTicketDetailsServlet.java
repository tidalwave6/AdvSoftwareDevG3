package com.g3app.controller;

import com.g3app.model.SupportTicket;
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
import java.util.List; // Import the List class

@WebServlet("/StaffViewTicketDetailsServlet")
public class StaffViewTicketDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketIdParam = request.getParameter("ticketId");
        if (ticketIdParam == null || ticketIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ticket ID is required.");
            return;
        }

        int ticketId;
        try {
            ticketId = Integer.parseInt(ticketIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Ticket ID format.");
            return;
        }
        
        try {
            // Establish database connection
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);

            // Retrieve the support ticket by ID
            SupportTicket ticket = dbManager.getSupportTicketById(ticketId);
            List<Message> messages = dbManager.getMessagesByTicketId(ticketId); // Retrieve messages by ticket ID

            // Set the ticket and messages in the request scope
            request.setAttribute("ticket", ticket);
            request.setAttribute("messages", messages); // Set messages in request scope

            // Close connection
            connector.closeConnection();

            // Forward to staffSupportTicketDetails.jsp
            request.getRequestDispatcher("staffSupportTicketDetails.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
