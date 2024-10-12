package com.g3app.controller;

import com.g3app.model.Shipment;
import com.g3app.dao.DBConnector;
import com.g3app.dao.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/DeleteShipmentServlet")
public class DeleteShipmentServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shipmentID = request.getParameter("id");

        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);
            Shipment shipment = dbManager.getShipmentByID(Integer.parseInt(shipmentID));
            dbManager.deleteShipment(shipment);
            connector.closeConnection();
            response.sendRedirect(request.getHeader("referer"));
            

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Shipment not deleted.");
        }
    }
}
