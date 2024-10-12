package com.g3app.controller;

import com.g3app.model.Book;
import com.g3app.dao.DBConnector;
import com.g3app.dao.DBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/BookAddServlet")
public class BookAddServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from request
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String publishedDate = request.getParameter("publishedDate");
        String genre = request.getParameter("genre");
        String medium = request.getParameter("medium");
        String description = request.getParameter("description");
        String imgUrl = request.getParameter("imgUrl");

        // Convert price to double
        double bookPrice = 0;
        try {
            bookPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            request.setAttribute("errorMessage", "Invalid price format.");
            request.getRequestDispatcher("staffCatalogue.jsp").forward(request, response);
            return;
        }

        // Create a new Book object (without ID, since it will be auto-generated)
        Book newBook = new Book(title, author, bookPrice, publishedDate, description, imgUrl, genre, medium);
        
        try {
            // Open database connection
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            DBManager dbManager = new DBManager(conn);
            
            // Add the book to the database
            dbManager.addBook(newBook);
            
            // Close database connection
            connector.closeConnection();
            
            // Set success message
            request.setAttribute("status", "success");
            request.setAttribute("successMessage", "Book added successfully!");
            request.getRequestDispatcher("staffCatalogue.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            request.setAttribute("errorMessage", "Failed to add the book.");
            request.getRequestDispatcher("staffCatalogue.jsp").forward(request, response);
        }
    }
}
