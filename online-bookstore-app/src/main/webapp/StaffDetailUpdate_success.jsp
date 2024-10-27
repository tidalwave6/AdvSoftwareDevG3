<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Account Update Status - Bookstore</title>
</head>
<body>
    <jsp:include page="staff-nav-header.jsp"/>

    <main>
        <section class="success-section">
            <img src="images/success.png" alt="Success">
            <h1>Details have been updated!</h1>
            <p>Your details have been updated.</p>
            <div class="success-buttons">
                <a href="staffDashboard.jsp" class="button">Home</a>
                <a href="mystaffdetails.jsp" class="button">My Account</a>
            </div>
        </section>
    </main><jsp:include page="footer.jsp"/>
</body>
</html>
