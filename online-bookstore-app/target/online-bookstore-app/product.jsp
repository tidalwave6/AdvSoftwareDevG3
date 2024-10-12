<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.g3app.model.*" %>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Products - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="animated">
            <h1 class="formTitle">FULL CATALOGUE</h1>
            <p>Explore our collection of books from various genres.</p>

            <div class="book-selection">
                <%
                    // Create a new instance of Catalogue and fetch all books
                    Catalogue catalogue = new Catalogue();
                    ArrayList<Book> books = catalogue.getAllBooks(); // Fetch all books from the database

                    // Check if there are books to display
                    if (books != null && !books.isEmpty()) {
                %>
                    <div class="book-selection"> <!-- Wrap all books in a single container -->
                        <%
                            for (int i = 0; i < books.size(); i++) {
                                // Display each book in a horizontal manner
                                Book book = books.get(i);
                        %>
                            <div class="book">
                                <a href="book-details.jsp?id=<%= book.getId() %>">
                                    <img src="<%= book.getImgUrl() %>" alt="<%= book.getTitle() %>">
                                    <div class="book-details">
                                        <h3><%= book.getTitle() %></h3>
                                        <p>Author: <%= book.getAuthor() %></p>
                                        <p>Price: $<%= book.getPrice() %></p>
                                    </div>
                                </a>
                            </div>
                        <%
                            }
                        %>
                    </div> <!-- Close book-selection div -->
                <%
                    } else {
                %>
                    <p>No books available at the moment.</p>
                <%
                    }
                %>
            </div> <!-- End of book-selection -->
        </section>
    </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>
