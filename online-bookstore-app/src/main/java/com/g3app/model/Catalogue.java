package com.g3app.model;
import java.sql.*;
import java.util.ArrayList;
import com.g3app.dao.*;

public class Catalogue {
    private DBConnector connector;
    private Connection conn;
    private DBManager db;
    
    public Catalogue(){
        try{
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBManager(conn);
        } catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
    }

    
    public ArrayList<Book> getAllBooks() throws SQLException{
        return db.getAllBooks();
    }
    
     public Book getBookById(int id) throws SQLException {
        return db.getBookById(id);
    }
    
   
}