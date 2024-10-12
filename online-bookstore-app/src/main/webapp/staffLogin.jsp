<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Staff Login - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>
    
    <main>
        <section class="form-section animated">
            <h1 class="formTitle">Staff Log In</h1>
            <form action="StaffLoginServlet" method="POST" class="form">
                <div class="form-group">
                    <label class="formLabel" for="staffEmail">Staff Email</label>
                    <input type="email" id="staffEmail" name="staffEmail" required>
                </div>
                <div class="form-group">
                    <label class="formLabel" for="staffPassword">Password</label>
                    <input type="password" id="staffPassword" name="staffPassword" required>
                </div>
                <%
                String errorMessage = request.getParameter("error");
                if (errorMessage != null) {
                %>
                    <p class="error-message" style="color: red;">Invalid email or password. Please try again.</p>
                <% 
                    }
                %>
                <input type="hidden" id="submitted" name="submitted" value="yes">
                <div class="centerDiv">
                    <button type="submit" class="button" id="submit" name="submit">Login</button><br>
                    <a href="staffRegister.jsp" class="register-link">Create a staff account</a> 
                </div>
            </form>
        </section>
    </main>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>
