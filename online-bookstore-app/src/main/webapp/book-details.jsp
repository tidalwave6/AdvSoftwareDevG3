<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.g3app.model.*" %>
<%@ page import="java.sql.*" %>

<%
    // Initialize variables
    String title = "Book Not Found";
    String message = (String) request.getAttribute("message");
    Book book = null;

    // Get the book ID from the request parameter
    String bookIdParam = request.getParameter("id");

    // Validate and fetch the book details
    if (bookIdParam != null && !bookIdParam.isEmpty()) {
        try {
            int bookId = Integer.parseInt(bookIdParam); // Parse the ID
            Catalogue catalogue = new Catalogue();
            book = catalogue.getBookById(bookId); // Retrieve the book object

            if (book != null) {
                title = book.getTitle(); // Set the title for the book if found
            }
        } catch (NumberFormatException e) {
            message = "Invalid book ID format.";
        } catch (SQLException e) {
            message = "Error fetching book details: " + e.getMessage();
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= title %> - Bookstore</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <!-- Display the message if it exists -->
        <% if (message != null) { %>
            <div class="alert alert-warning">
                <p><%= message %></p>
            </div>
        <% } %>

        <% if (book != null) { %>
            <h1><%= book.getTitle() %> - Bookstore</h1>
            <div class="individual-book-details">
                <img src="<%= book.getImgUrl() %>" alt="<%= book.getTitle() %>" class="book-img">
                <div class="book-info">
                    <h2><%= book.getTitle() %></h2>
                    <p><strong>Author:</strong> <%= book.getAuthor() %></p>
                    <p><strong>Medium:</strong> <%= book.getMedium() %></p>
                    <p><strong>Price:</strong> $<%= book.getPrice() %></p>
                    <p><strong>Genre:</strong> <%= book.getGenre() %></p>
                    <p><strong>Description:</strong> <%= book.getDescription() %></p>

                    <!-- Add to Cart Form with Editable Quantity -->
                    <div class="add-to-cart">
                        <form action="cart.jsp" method="POST">
                            <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                            <input type="hidden" name="title" value="<%= book.getTitle() %>">
                            <input type="hidden" name="price" value="<%= book.getPrice() %>">
                            
                            <!-- Editable Quantity Input -->
                            <label for="quantity">Quantity:</label>
                            <input type="number" id="quantity" name="quantity" value="1" min="1">
                            
                            <button type="submit" class="btn-add-to-cart">Add to Cart</button>
                        </form>
                    </div>

                    <div class="add-to-wishlist">
                        <form action="wishlist" method="POST">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                            <button type="submit" class="btn-add-to-wishlist">Add to Wishlist</button>
                        </form>
                    </div>
                </div>
            </div>
        <% } else { %>
            <div class="error-message">
                <h2>Book Not Found</h2>
                <p>The book you are looking for does not exist.</p>
                <a href="product.jsp" class="button">Back to Catalogue</a>
            </div>
        <% } %>
    </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>
