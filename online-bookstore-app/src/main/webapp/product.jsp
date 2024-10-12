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

            <!-- Multi-filter search bar -->
            <div class="search-bar">
                <form action="products.jsp" method="get" class="filter-form">
                    <div class="filter-container">
                        <!-- Search by title or keyword -->
                        <div class="filter-item">
                            <input type="text" name="search" placeholder="Search for books..." class="search-input">
                        </div>

                        <!-- Filter by genre -->
                        <div class="filter-item">
                            <select name="genre" class="filter-dropdown">
                                <option value="">All Genres</option>
                                <option value="fiction">Fiction</option>
                                <option value="nonfiction">Non-fiction</option>
                                <option value="mystery">Mystery</option>
                                <option value="fantasy">Fantasy</option>
                                <option value="sci-fi">Sci-Fi</option>
                                <option value="biography">Biography</option>
                            </select>
                        </div>

                        <!-- Filter by price range -->
                        <div class="filter-item">
                            <select name="priceRange" class="filter-dropdown">
                                <option value="">All Prices</option>
                                <option value="0-10">$0 - $10</option>
                                <option value="10-20">$10 - $20</option>
                                <option value="20-50">$20 - $50</option>
                                <option value="50-100">$50 - $100</option>
                                <option value="100+">$100+</option>
                            </select>
                        </div>

                        <!-- Filter by author -->
                        <div class="filter-item">
                            <input type="text" name="author" placeholder="Author name..." class="filter-input">
                        </div>

                        <!-- Filter by format -->
                        <div class="filter-item">
                            <select name="format" class="filter-dropdown">
                                <option value="">All Formats</option>
                                <option value="hardcover">Hardcover</option>
                                <option value="paperback">Paperback</option>
                                <option value="ebook">eBook</option>
                                <option value="audiobook">Audiobook</option>
                            </select>
                        </div>

                        <!-- Filter by availability -->
                        <div class="filter-item">
                            <select name="availability" class="filter-dropdown">
                                <option value="">All Availability</option>
                                <option value="in-stock">In Stock</option>
                                <option value="out-of-stock">Out of Stock</option>
                                <option value="preorder">Preorder</option>
                            </select>
                        </div>
                    </div>

                    <div class="submit-container">
                        <button type="submit" class="search-button">Search</button>
                    </div>
                </form>
            </div>

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
