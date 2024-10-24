<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.g3app.model.Book" %>
<%@ page import="java.util.List" %>

<%
    List<Book> wishlist = (List<Book>) session.getAttribute("wishlist");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Wishlist</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <style>
        /* Wishlist Page Styles */
        .wishlist-items {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .wishlist-item {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        }

        .wishlist-book-img {
            width: 100px;
            height: 150px;
            object-fit: cover;
            border-radius: 8px;
            margin-right: 15px;
        }

        .wishlist-book-info {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .wishlist-book-info h3 {
            margin: 0 0 10px 0;
            font-size: 18px;
        }

        .wishlist-book-info p {
            margin: 0;
            font-size: 14px;
            color: #555;
        }

        .wishlist-actions {
            display: flex;
            gap: 10px;
            margin-top: 10px;
            width: 100%; /* Make sure the buttons fill the container evenly */
        }

        .btn-remove-from-wishlist,
        .btn-add-to-cart {
            flex: 1; /* Ensure both buttons take up equal space */
            background-color: #FF6F61;
            border: none;
            color: white;
            padding: 12px 0; /* Adjust padding for better appearance */
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            font-size: 16px; /* Improve button text size */
            transition: background-color 0.3s ease;
        }

        .btn-remove-from-wishlist:hover,
        .btn-add-to-cart:hover {
            background-color: #e04b47;
        }
    </style>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <div class="container wishlist-container">
        <h2>Your Wishlist</h2>

        <%
            if (wishlist == null || wishlist.isEmpty()) {
        %>
            <p>Your wishlist is empty. <a href="product.jsp">Browse books</a> to add items to your wishlist!</p>
        <% 
            } else { 
        %>
            <ul class="wishlist-items">
                <% 
                    for (Book book : wishlist) { 
                %>
                    <li class="wishlist-item">
                        <img src="<%= book.getImgUrl() %>" 
                             alt="<%= book.getTitle() %>" 
                             style="width: 100px; height: 150px; object-fit: cover; border-radius: 8px; margin-right: 15px;">
                        <div class="wishlist-book-info">
                            <a href="book-details.jsp?id=<%= book.getBookId() %>"> <%= book.getTitle() %></a>
                            <p>Author: <%= book.getAuthor() %></p>
                            <p>Price: $<%= book.getPrice() %></p>
                            <div class="wishlist-actions">
                                <!-- Remove from Wishlist -->
                                <form action="wishlist" method="POST">
                                    <input type="hidden" name="action" value="remove">
                                    <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                                    <button type="submit" class="btn-remove-from-wishlist">Remove</button>
                                </form>

                                <!-- Add to Cart -->
                                <form action="cart.jsp" method="POST">
                                    <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                                    <button type="submit" class="btn-add-to-cart">Add to Cart</button>
                                </form>
                            </div>
                        </div>
                    </li>
                <% 
                    } 
                %>
            </ul>
        <% 
            } 
        %>

        <a href="product.jsp" class="button">Continue Shopping</a>
    </div>

    <jsp:include page="footer.jsp"/>
</body>
</html>
