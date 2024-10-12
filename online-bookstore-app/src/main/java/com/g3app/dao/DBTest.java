package com.g3app.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTest {
    public static void main(String[] args) {
        DBConnector dbConnector = null;
        try {
            // Initialize the DBConnector
            dbConnector = new DBConnector();
            // Open the connection
            Connection conn = dbConnector.openConnection();

            // Check if connection is not null
            if (conn != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to make connection!");
            }

            // Close the connection
            dbConnector.closeConnection();
        } catch (ClassNotFoundException e) {
            System.out.println("Database Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        } finally {
            if (dbConnector != null) {
                try {
                    dbConnector.closeConnection(); // Ensure the connection is closed
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
