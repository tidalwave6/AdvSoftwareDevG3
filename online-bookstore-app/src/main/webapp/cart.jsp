<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>

<%
    // Retrieve or initialize the cart in the session
    HashMap<Integer, HashMap<String, Object>> cart = (HashMap<Integer, HashMap<String, Object>>) session.getAttribute("cart");
    if (cart == null) {
        cart = new HashMap<>();
    }

    // Retrieve parameters from the request
    String bookIdParam = request.getParameter("bookId");
    String title = request.getParameter("title");
    String priceParam = request.getParameter("price");
    String quantityParam = request.getParameter("quantity");

    if (bookIdParam != null && title != null && priceParam != null && quantityParam != null) {
        try {
            int bookId = Integer.parseInt(bookIdParam);
            double price = Double.parseDouble(priceParam);
            int quantity = Integer.parseInt(quantityParam);

            // Check if the book is already in the cart
            if (cart.containsKey(bookId)) {
                // Update quantity if book already exists in the cart
                HashMap<String, Object> itemDetails = cart.get(bookId);
                int existingQuantity = (int) itemDetails.get("quantity");
                itemDetails.put("quantity", existingQuantity + quantity);
            } else {
                // Add new book to cart
                HashMap<String, Object> itemDetails = new HashMap<>();
                itemDetails.put("title", title);
                itemDetails.put("price", price);
                itemDetails.put("quantity", quantity);
                cart.put(bookId, itemDetails);
            }

            // Update cart in session
            session.setAttribute("cart", cart);

            // Confirmation message
            request.setAttribute("message", "Book added to cart!");

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid input for book ID, price, or quantity.");
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <style>
        .button-container {
            display: flex;
            gap: 10px; /* Space between buttons */
        }

        .continue-shopping-button {
            background-color: #28a745; /* Green color */
            border: none;
            color: white;
            padding: 12px 20px;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .continue-shopping-button:hover {
            background-color: #218838; /* Darker green */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <h1>Shopping Cart</h1>

        <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-info">
                <p><%= request.getAttribute("message") %></p>
            </div>
        <% } %>

        <section class="cart-details">
            <form action="checkout.jsp" method="POST">
                <table>
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            double totalPrice = 0.0;

                            for (Integer id : cart.keySet()) {
                                HashMap<String, Object> item = cart.get(id);
                                String itemName = (String) item.get("title");
                                double itemPrice = (double) item.get("price");
                                int itemQuantity = (int) item.get("quantity");
                                double itemTotal = itemPrice * itemQuantity;
                                totalPrice += itemTotal;
                        %>
                        <tr>
                            <td><%= itemName %></td>
                            <td><%= itemQuantity %></td>
                            <td>$<%= itemPrice %></td>
                            <td>$<%= itemTotal %></td>
                            <td>
                                <!-- Remove item button -->
                                <form action="removeItem.jsp" method="POST" style="display:inline;">
                                    <input type="hidden" name="bookId" value="<%= id %>">
                                    <button type="submit" class="button">Remove</button>
                                </form>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <div class="cart-summary">
                    <p><strong>Total Price:</strong> $<%= totalPrice %></p>
                    <input type="hidden" name="total" value="<%= totalPrice %>">

                    <div class="button-container">
                        <a href="product.jsp" class="continue-shopping-button">Continue Shopping</a>
                        <button type="submit" class="button">Proceed to Checkout</button>
                    </div>
                </div>
            </form>
        </section>
    </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>
