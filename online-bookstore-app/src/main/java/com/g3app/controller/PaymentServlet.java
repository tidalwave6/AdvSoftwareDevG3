package com.g3app.controller;

import com.g3app.model.Payment;
import com.g3app.dao.DBConnector;
import com.g3app.dao.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cardHolderName = request.getParameter("cardHolderName");

        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);

            if ("add".equals(action)) {
                // Add new payment method
                Payment newPayment = new Payment(cardNumber, expiryDate, cardHolderName);
                dbManager.addPaymentMethod(newPayment);
            } else if ("edit".equals(action)) {
                // Edit existing payment method
                int paymentId = Integer.parseInt(request.getParameter("paymentId"));
                Payment updatedPayment = new Payment(paymentId, cardNumber, expiryDate, cardHolderName);
                dbManager.updatePaymentMethod(updatedPayment);
            } else if ("delete".equals(action)) {
                // Delete payment method
                int paymentId = Integer.parseInt(request.getParameter("paymentId"));
                dbManager.deletePaymentMethod(paymentId);
            }
            
            connector.closeConnection();
            response.sendRedirect("payment.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Payment operation failed.");
        }
    }
}
