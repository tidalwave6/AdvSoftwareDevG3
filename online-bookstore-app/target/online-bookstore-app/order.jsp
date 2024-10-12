<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Orders - Bookstore</title>
</head>
<body>

    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="order-section animated">
            <h1 class="formTitle">Your Orders</h1>

            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Books Ordered</th>
                        <th>Status</th>
                        <th>Shipping Details</th>
                        <th>Date Placed</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>10001</td>
                        <td>Book 1, Book 2</td>
                        <td>Shipped</td>
                        <td>address1</td>
                        <td>2024-09-01</td>
                        <td><a href="shipment_tracking.jsp" class="button">Track</a></td>
                    </tr>
                    <tr>
                        <td>10002</td>
                        <td>Book 3</td>
                        <td>Processing</td>
                        <td>address2</td>
                        <td>2024-09-03</td>
                        <td><button class="button">Cancel</button></td>
                    </tr>
                </tbody>
            </table>
        </section>
    </main><jsp:include page="footer.jsp"/>
</body>
</html>
