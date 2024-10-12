<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.g3app.model.*" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="animated">

            <%
                // Get the book ID from the request parameter
                String bookIdParam = request.getParameter("id");
                Book book = null;

                // Validate and fetch the book details
                if (bookIdParam != null && !bookIdParam.isEmpty()) {
                    try {
                        int bookId = Integer.parseInt(bookIdParam); // Parse the ID
                        Catalogue catalogue = new Catalogue();
                        book = catalogue.getBookById(bookId); // Retrieve the book object
                    } catch (NumberFormatException e) {
                        out.println("Invalid book ID format."); // Invalid ID error
                    } catch (SQLException e) {
                        out.println("Error fetching book details: " + e.getMessage()); // SQL error
                    }
                }

                // Check if the book exists
                String title;
                if (book != null){
                title = book.getTitle();
                }
                else{
                title = "Book not found.";
                }
                if (book != null) {
            %>
            <title><%= title%> - Bookstore</title>
            <h1 class="formTitle"> Catalogue -> <%=book.getMedium()%></h1>
                <div class="individual-book-details">
                    <img src="<%= book.getImgUrl() %>" alt="<%= book.getTitle() %>" class="book-img">
                    <div class="book-info">
                        <h2><%= book.getTitle() %></h2>
                        <p><strong>Author:</strong> <%= book.getAuthor() %> <span>
                        <p><strong>Medium:</strong> <%= book.getMedium() %></span></p>
                        <p><strong>Price:</strong> $<%= book.getPrice() %></p>
                        <p><strong>Genre:</strong> <%= book.getGenre() %></p>
                        <p><strong>Description:</strong> <%= book.getDescription() %></p><div class="add-to-cart">
    <form action="cart.jsp" method="POST">
        <input type="hidden" name="bookId" value="<%= book.getId() %>">
        <button type="submit" class="btn-add-to-cart">Add to Cart</button>
    </form>
</div>
                    </div>
                    
                </div>
                    
            <%
                } else {
                    out.println("No book found with the specified ID."); // Book not found message
                }
            %>
        </section>
    </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>
