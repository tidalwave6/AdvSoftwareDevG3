<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.g3app.model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>

    <main>
        <section class="animated">
            <%
                // Retrieve user from session
                User user = (User) request.getSession().getAttribute("user");
                if (user != null) {
                    String welcomeMessage = "Welcome, " + user.getFirstName() + "!";
                    out.println("<h1>" + welcomeMessage + "</h1>");
                }
                else {
                    String guestWelcome = "Welcome to Bookstore!";
                    out.println("<h1>" + guestWelcome + "</h1>");
                }
            %>
            <p>Your one-stop shop for all your reading needs, with a wide range of non-fiction, fiction and all your favourite writers. Niche or famous, we've got them all.</p>
            
            <!-- Book Selection Section -->
        <div class="center"><h1>BEST SELLERS</h1></div>
        
        <section class="book-selection">
            <div class="book">
                <a href="book-details.jsp?id=1">
                    <img src="images/book1.jpg" alt="Book 1">
                </a>
            </div>
            <div class="book">
                <a href="book-details.jsp?id=2">
                    <img src="images/book2.jpg" alt="Book 2">
                </a>
            </div>
            <div class="book">
                <a href="book-details.jsp?id=3">
                    <img src="images/book3.jpg" alt="Book 3">
                </a>
            </div>
        </section>

        <div class="see-more">
            <a href="product.jsp" class="button">See All Books</a>
        </div>
        
        </section>
    </main>
    <jsp:include page="footer.jsp"/>
</body>
</html>
