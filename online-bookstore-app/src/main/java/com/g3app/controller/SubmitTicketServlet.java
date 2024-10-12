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
import java.util.Date;

@WebServlet("/SubmitTicketServlet")
public class SubmitTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters from the request
        String customer_name = request.getParameter("customer_name");
        String email = request.getParameter("email");
        String subject_title = request.getParameter("subject_title");
        String type_of_enquiry = request.getParameter("type_of_enquiry");
        String issue_description = request.getParameter("issue_description");

        try {
            // Establish database connection
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);

            // Create new support ticket object using the existing constructor
            SupportTicket ticket = new SupportTicket(0, customer_name, email, subject_title, type_of_enquiry, issue_description, "Open", new Date());

            // Add the ticket to the database
            dbManager.createSupportTicket(ticket);
            connector.closeConnection();

            // Redirect to success page after submission
            response.sendRedirect("supportTicket_success.jsp"); // Redirect to a success page
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ticket submission failed.");
        }
    }
}