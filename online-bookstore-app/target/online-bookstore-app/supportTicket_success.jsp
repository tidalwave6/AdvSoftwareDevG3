<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Support Ticket Success - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="success-section">
            <img src="images/success.png" alt="Success">
            <h1>Request has been sent!</h1>
            <p>Thank you for submitting your request. We will get back to you shortly.</p>
            <div class="success-buttons">
<!--                <a href="viewTicket.jsp" class="button">View ticket details</a> ticketid needs to be passed here IN PROGRESS-->
                <a href="UserSupportDashboardServlet" class="button">Back to dashboard</a>
            </div>
        </section>
    </main>
</body>
</html>
