
package com.webapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.g3app.dao.DBManager;
import com.g3app.model.Shipment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShipmentTest {
    
    private DBManager dbManager;
    private Connection connection;
    
    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "root");
        dbManager = new DBManager(connection);
    }
    
    
    @Test
    public void createShipment() throws SQLException{
        if(dbManager.getShipmentByID(1000)!=null){dbManager.deleteShipment(dbManager.getShipmentByID(1000));}
        Shipment shipment = new Shipment();
        shipment.setID(1000);
        shipment.setDate("2024-01-01");
        shipment.setProgress("Completed");
        shipment.setStatus("Delivered");
        dbManager.createShipment(shipment);
        
        Shipment testShipment = dbManager.getShipmentByID(1000);
        Assert.assertEquals("2024-01-01", testShipment.getDate());
        Assert.assertEquals("Completed", testShipment.getProgress());
        Assert.assertEquals("Delivered", testShipment.getStatus());
        dbManager.deleteShipment(testShipment);
    }
    
    @Test
    public void readShipment() throws SQLException{
        if(dbManager.getShipmentByID(1000)!=null){dbManager.deleteShipment(dbManager.getShipmentByID(1000));}
        Shipment shipment = new Shipment();
        shipment.setID(1000);
        shipment.setDate("2024-01-01");
        shipment.setProgress("Completed");
        shipment.setStatus("Delivered");
        dbManager.createShipment(shipment);
        
        boolean isRead = dbManager.readShipment();
        Assert.assertTrue(isRead);
        
        dbManager.deleteShipment(shipment);
    }
    
    @Test
    public void updateShipment()throws SQLException{
        if(dbManager.getShipmentByID(1000)!=null){dbManager.deleteShipment(dbManager.getShipmentByID(1000));}
        Shipment shipment = new Shipment();
        shipment.setID(1000);
        shipment.setDate("2024-01-01");
        shipment.setProgress("Completed");
        shipment.setStatus("Delivered");
        dbManager.createShipment(shipment);
        
        Shipment newShipment = new Shipment();
        newShipment.setID(1000);
        newShipment.setDate("2024-12-12");
        newShipment.setProgress("In Progress");
        newShipment.setStatus("On the way");
        dbManager.updateShipment(newShipment);
        
        Shipment testShipment = dbManager.getShipmentByID(1000);
        Assert.assertEquals("2024-12-12", testShipment.getDate());
        Assert.assertEquals("In Progress", testShipment.getProgress());
        Assert.assertEquals("On the way", testShipment.getStatus());
        dbManager.deleteShipment(testShipment);
    }
    
    @Test
    public void deleteShipment()throws SQLException{
        if(dbManager.getShipmentByID(1000)!=null){dbManager.deleteShipment(dbManager.getShipmentByID(1000));}
        Shipment shipment = new Shipment();
        shipment.setID(1000);
        shipment.setDate("2024-01-01");
        shipment.setProgress("Completed");
        shipment.setStatus("Delivered");
        dbManager.createShipment(shipment);
        Assert.assertTrue(dbManager.getShipmentByID(1000) != null);
        dbManager.deleteShipment(shipment);
        Assert.assertTrue(dbManager.getShipmentByID(1000) == null);
    }
}
