package com.g3app.controller;

import com.g3app.model.SupportTicket;
import com.g3app.dao.DBConnector;
import com.g3app.dao.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/UserSupportDashboardServlet")
public class UserSupportDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming user email is stored in the session after login
        String userEmail = (String) request.getSession().getAttribute("email");

        try {
            // Establish database connection
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);

            // Retrieve support tickets for the user
            List<SupportTicket> tickets = dbManager.getTicketsByEmail(userEmail);

            // Set the tickets list in the request scope
            request.setAttribute("tickets", tickets);

            // Close connection
            connector.closeConnection();

            // Forward to contact.jsp
            request.getRequestDispatcher("contact.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load support tickets.");
        }
    }
}
