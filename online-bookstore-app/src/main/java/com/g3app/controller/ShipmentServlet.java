package com.g3app.controller;

import com.g3app.model.Shipment;
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

@WebServlet("/shipment")
public class ShipmentServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Shipment shipment = (Shipment) session.getAttribute("shipment");
        String shipmentID = request.getParameter("id");
        
        String action = request.getParameter("action");
        if (shipment == null) {
            session.setAttribute("shipment", shipment);
        }

        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);
            if("delete".equals(action)){
                Shipment shipmentToDelete = dbManager.getShipmentByID(Integer.parseInt(shipmentID));
                dbManager.deleteShipment(shipmentToDelete);
            }
            if("update".equals(action)){
                Shipment updatedShipment = new Shipment();
                updatedShipment.setID(Integer.parseInt(request.getParameter("id")));
                updatedShipment.setDate(request.getParameter("date"));
                updatedShipment.setProgress(request.getParameter("progress"));
                updatedShipment.setStatus(request.getParameter("status"));
                dbManager.updateShipment(updatedShipment);            
            }
            if("create".equals(action)){
                Shipment newShipment = new Shipment();
                newShipment.setID(1000);
                newShipment.setDate("2000-01-01");
                newShipment.setProgress("dummy");
                newShipment.setStatus("dummy");
                dbManager.createShipment(newShipment);
            }
           
            connector.closeConnection();
            response.sendRedirect("shipment_table.jsp");
            

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Shipment not deleted.");
        }
    }
}
