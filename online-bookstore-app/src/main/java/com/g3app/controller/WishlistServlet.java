package com.g3app.controller;

import com.g3app.dao.DBManager;
import com.g3app.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {

    private DBManager dbManager;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize the database connection and DBManager instance
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookstoredb", "root", "Passwerd1!"); // Replace with your credentials
            dbManager = new DBManager(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize DBManager", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> wishlist = (List<Book>) session.getAttribute("wishlist");

        if (wishlist == null) {
            wishlist = new ArrayList<>();
            session.setAttribute("wishlist", wishlist);
        }

        String action = request.getParameter("action");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        try {
            if ("add".equals(action)) {
                // Fetch the book from the database
                Book book = dbManager.getBookById(bookId);
                if (book != null) {
                    // Check if the book is already in the wishlist
                    boolean exists = wishlist.stream().anyMatch(b -> b.getBookId() == bookId);
                    if (!exists) {
                        wishlist.add(book);
                        response.sendRedirect("wishlist.jsp"); // Redirect if added successfully
                    } else {
                        // Set a message indicating the book is already in the wishlist
                        request.setAttribute("message", "This book is already in your wishlist.");
                        request.getRequestDispatcher("book-details.jsp?id=" + bookId).forward(request, response);
                        return; // Stop further execution
                    }
                }
            } else if ("remove".equals(action)) {
                // Remove the book from the wishlist
                wishlist.removeIf(b -> b.getBookId() == bookId);
                response.sendRedirect("wishlist.jsp"); // Redirect after removal
                return; // Stop further execution
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error occurred.", e);
        }
    }
}
