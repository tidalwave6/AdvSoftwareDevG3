<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap, java.util.ArrayList" %>

<%
    // Retrieve or initialize the cart in the session
    HashMap<Integer, HashMap<String, Object>> cart = (HashMap<Integer, HashMap<String, Object>>) session.getAttribute("cart");
    if (cart == null) {
        cart = new HashMap<>();
        session.setAttribute("cart", cart);
    }

    // Calculate total amount
    double totalAmount = 0.0;
    for (HashMap<String, Object> item : cart.values()) {
        double price = (double) item.get("price");
        int quantity = (int) item.get("quantity");
        totalAmount += price * quantity;
    }

    // Store order information in the session for confirmation
    session.setAttribute("orderID", "ORD12345"); // Example order ID, replace with actual logic if needed
    session.setAttribute("totalAmount", totalAmount);

    // Prepare order items list for confirmation page
    ArrayList<HashMap<String, Object>> orderItems = new ArrayList<>();
    for (Integer id : cart.keySet()) {
        HashMap<String, Object> itemDetails = new HashMap<>();
        itemDetails.put("title", cart.get(id).get("title"));
        itemDetails.put("price", cart.get(id).get("price"));
        itemDetails.put("quantity", cart.get(id).get("quantity"));
        itemDetails.put("imgUrl", cart.get(id).get("imgUrl")); // Store image URL for confirmation page
        orderItems.add(itemDetails);
    }
    session.setAttribute("orderItems", orderItems);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Checkout - Bookstore</title>
    <style>
        .checkout-container {
            display: flex;
            gap: 40px;
            margin-top: 20px;
        }

        .payment-section, .shipment-section {
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            flex: 1;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .button-container button {
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
    <script>
        function maskCardNumberOnBlur(input) {
            const value = input.value.replace(/\s+/g, '').slice(0, 16); // Only the first 16 digits
            if (value.length === 16) {
                input.value = `${value.slice(0, 12)} ****`;
            }
        }

        function unmaskCardNumberOnFocus(input) {
            input.value = input.dataset.raw || ''; // Show unmasked version
        }

        function updateRawCardNumber(input) {
            const value = input.value.replace(/\D/g, ''); // Remove non-numeric characters
            input.dataset.raw = value; // Store unmasked version in data attribute
            input.value = value.replace(/(\d{4})(?=\d)/g, '$1 '); // Format with spaces after every 4 digits
        }
    </script>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="animated">
            <h1 class="formTitle">Checkout</h1>

            <div class="order-summary">
                <h2>Order Summary</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Integer id : cart.keySet()) {
                                HashMap<String, Object> item = cart.get(id);
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
                        <% } %>
                    </tbody>
                </table>
                <h3>Total: $<%= String.format("%.2f", totalAmount) %></h3>
            </div>

            <form action="payment-confirmation.jsp" method="POST">
                <div class="checkout-container">
                    <div class="payment-section">
                        <h2>Payment Details</h2>
                        <div class="form-group">
                            <label for="cardholderName">Cardholder Name:</label>
                            <input type="text" id="cardholderName" name="cardholderName" placeholder="John Doe" required>
                        </div>
                        <div class="form-group">
                            <label for="cardNumber">Card Number:</label>
                            <input type="text" id="cardNumber" name="cardNumber" placeholder="1234 5678 9123 ****" 
                                   oninput="updateRawCardNumber(this)" 
                                   onfocus="unmaskCardNumberOnFocus(this)" 
                                   onblur="maskCardNumberOnBlur(this)" required>
                        </div>
                        <div class="form-group">
                            <label for="expiryDate">Expiry Date:</label>
                            <input type="text" id="expiryDate" name="expiryDate" maxlength="5" placeholder="MM/YY" required>
                        </div>
                        <div class="form-group">
                            <label for="cvv">CVV:</label>
                            <input type="text" id="cvv" name="cvv" maxlength="3" placeholder="123" required>
                        </div>
                    </div>

                    <div class="shipment-section">
                        <h2>Shipment Information</h2>
                        <div class="form-group">
                            <label for="recipientName">Recipient Name:</label>
                            <input type="text" id="recipientName" name="recipientName" placeholder="John Doe" required>
                        </div>
                        <div class="form-group">
                            <label for="address">Address:</label>
                            <input type="text" id="address" name="address" placeholder="123 Main St" required>
                        </div>
                        <div class="form-group">
                            <label for="city">City:</label>
                            <input type="text" id="city" name="city" placeholder="City" required>
                        </div>
                        <div class="form-group">
                            <label for="zipCode">ZIP Code:</label>
                            <input type="text" id="zipCode" name="zipCode" placeholder="1234" required>
                        </div>
                    </div>
                </div>

                <div class="button-container">
                    <button type="submit" class="button">Complete Purchase</button>
                </div>
            </form>
        </section>
    </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>
