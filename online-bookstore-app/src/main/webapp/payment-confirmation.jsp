<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap, java.util.ArrayList" %>

<%
    // Retrieve order details from the session
    String orderID = (String) session.getAttribute("orderID");
    String recipientName = (String) session.getAttribute("recipientName"); 
    Double totalAmount = (Double) session.getAttribute("totalAmount");
    ArrayList<HashMap<String, Object>> orderItems = (ArrayList<HashMap<String, Object>>) session.getAttribute("orderItems");

    // Default values if session attributes are missing
    if (orderID == null) orderID = "Unknown Order ID";
    if (recipientName == null) recipientName = "Customer";
    if (totalAmount == null) totalAmount = 0.0;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <style>
        .confirmation-section {
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .order-summary p {
            font-size: 18px;
            margin: 10px 0;
            color: #333;
        }
        .order-items {
            margin-top: 20px;
        }
        .order-table th, .order-table td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        .order-table th {
            background-color: #333;
            color: #fff;
        }
    </style>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="confirmation-section">
            <h1>Order Confirmation</h1>
            <p>Thank you for your order! Here are your order details:</p>
            
            <div class="order-summary">
                <p><strong>Order ID:</strong> <%= orderID %></p>
                <p><strong>Recipient Name:</strong> <%= recipientName %></p>
                <p><strong>Total Cost:</strong> $<%= String.format("%.2f", totalAmount) %></p>
            </div>

            <div class="order-items">
                <h2>Order Items</h2>
                <table class="order-table">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (orderItems != null && !orderItems.isEmpty()) {
                                for (HashMap<String, Object> item : orderItems) {
                                    String title = (String) item.get("title");
                                    double price = (double) item.get("price");
                                    int quantity = (int) item.get("quantity");
                                    double subtotal = price * quantity;
                        %>
                        <tr>
                            <td><%= title %></td>
                            <td>$<%= String.format("%.2f", price) %></td>
                            <td><%= quantity %></td>
                            <td>$<%= String.format("%.2f", subtotal) %></td>
                        </tr>
                        <%      
                                }
                            } else {
                        %>
                        <tr><td colspan="4">No items found in the order.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </section>
        <a href="product.jsp" class="button">Continue Shopping</a>
    </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>
