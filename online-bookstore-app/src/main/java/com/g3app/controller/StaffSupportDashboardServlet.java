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

@WebServlet("/StaffSupportDashboardServlet")
public class StaffSupportDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Establish database connection
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);

            // Retrieve opened and closed support tickets
            List<SupportTicket> openedTickets = dbManager.getOpenedSupportTickets();
            List<SupportTicket> closedTickets = dbManager.getClosedSupportTickets();

            // Set the tickets lists in the request scope
            request.setAttribute("openedTickets", openedTickets);
            request.setAttribute("closedTickets", closedTickets);

            // Close connection
            connector.closeConnection();

            // Forward to staffSupportDashboard.jsp
            request.getRequestDispatcher("staffSupportDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
