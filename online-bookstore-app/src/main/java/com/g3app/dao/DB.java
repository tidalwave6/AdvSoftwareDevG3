package com.g3app.dao;

import java.sql.Connection;

public abstract class DB {
    protected String URL = "jdbc:mysql://localhost:3306/";
    protected String db = "bookstoredb"; // Database name
    protected String dbuser = "root"; // Your MySQL username
    protected String dbpass = "root"; // Your MySQL password
    protected String driver = "com.mysql.cj.jdbc.Driver"; // MySQL driver
    protected Connection conn; // Connection instance to be initialized in sub-classes
}