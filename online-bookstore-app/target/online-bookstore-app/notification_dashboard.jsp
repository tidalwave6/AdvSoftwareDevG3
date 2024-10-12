<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Notification Dashboard - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="notification-section animated">
            <h1>Notification Dashboard</h1>
            <p>Here you can view and manage all your notifications. Use the tabs below to navigate between viewing notifications and any other relevant sections.</p>

            <div id="notifications" class="tab-content active">
                <h2>Your Notifications</h2>
                <p>Here you can view and manage all your notifications. Click on a notification to view its details.</p>
                
                <!-- Example information, TO BE REPLACED WITH DATABASE CONTENT -->
                <table>
                    <thead>
                        <tr>
                            <th>Notification ID</th>
                            <th>Type</th> 
                            <th>Title</th>
                            <th>Details</th>
                            <th>Date Received</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Order</td> 
                            <td>Order Shipped</td>
                            <td>Your order #12345 has been shipped.</td>
                            <td>2024-09-01</td>
                            <td>Unread</td>
                            <td><a href="notification_detail.jsp?id=1" class="button">View</a></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Payment</td>
                            <td>Payment Successful</td>
                            <td>Your payment for order #67890 was successful.</td>
                            <td>2024-08-25</td>
                            <td>Read</td>
                            <td><a href="notification_detail.jsp?id=2" class="button">View</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>
    </main>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            // Tab functionality
            const tabs = document.querySelectorAll('.tab');
            const tabContents = document.querySelectorAll('.tab-content');

            tabs.forEach(tab => {
                tab.addEventListener('click', () => {
                    const targetId = tab.getAttribute('data-target');

                    tabs.forEach(t => t.classList.remove('active'));
                    tabContents.forEach(content => content.classList.remove('active'));

                    tab.classList.add('active');
                    document.getElementById(targetId).classList.add('active');
                });
            });
        });
    </script>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
