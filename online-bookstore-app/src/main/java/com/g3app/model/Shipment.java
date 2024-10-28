package com.g3app.model;

import com.g3app.dao.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Shipment {
    private int shipmentID;
    private String shipmentDate;
    private String shipmentProgress;
    private String shipmentStatus;
    
    public int getID(){return shipmentID;}
    public String getDate(){return shipmentDate;}
    public String getProgress(){return shipmentProgress;}
    public String getStatus(){return shipmentStatus;}
    
    public void setID(int shipmentID){this.shipmentID = shipmentID;}
    public void setDate(String shipmentDate){this.shipmentDate = shipmentDate;}
    public void setProgress(String shipmentProgress){this.shipmentProgress = shipmentProgress;}
    public void setStatus(String shipmentStatus){this.shipmentStatus = shipmentStatus;}
    
    public ArrayList<Shipment> getAllShipments() throws Exception {
    DBConnector connector = new DBConnector();
    Connection conn = connector.openConnection();
    Statement st=conn.createStatement();
    String query = "SELECT * FROM shipments";
    ResultSet rs = st.executeQuery(query);
    ArrayList<Shipment> shipments = new ArrayList<>();
    while (rs.next()) {
        Shipment shipment = new Shipment();
        shipment.setID(rs.getInt("ShipmentID"));
        shipment.setDate(rs.getString("ShipmentDate"));
        shipment.setProgress(rs.getString("ShipmentProgress"));
        shipment.setStatus(rs.getString("ShipmentStatus"));
        shipments.add(shipment);
    }
    connector.closeConnection();
    return shipments;
    }
    
    public Shipment getShipmentFromID(int id) throws Exception {
    DBConnector connector = new DBConnector();
    Connection conn = connector.openConnection();
    Statement st=conn.createStatement();
    String query = "SELECT * FROM shipments WHERE ShipmentID = ?";
        PreparedStatement statement = st.getConnection().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            Shipment shipment = new Shipment();
            shipment.setID(rs.getInt("ShipmentID"));
            shipment.setDate(rs.getString("ShipmentDate"));
            shipment.setProgress(rs.getString("ShipmentProgress"));
            shipment.setStatus(rs.getString("ShipmentStatus"));
            connector.closeConnection();
            return shipment;
            }
        connector.closeConnection();
        return null;
    }
    
    
}
