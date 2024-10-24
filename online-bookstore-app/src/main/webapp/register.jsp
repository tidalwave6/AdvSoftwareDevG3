<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Register - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>
    
    <main>
        <section class="form-section animated">
            <h1 class="formTitle">Customer Registration</h1>
            <form action="RegisterServlet" method="POST" class="form">
                <div class="form-group">
                    <label class="formLabel" for="firstName">First Name</label>
                    <input type="text" id="firstName" name="firstName" required>
                </div>
                <div class="form-group">
                    <label class="formLabel" for="lastName">Last Name</label>
                    <input type="text" id="lastName" name="lastName" required>
                </div>
                <div class="form-group">
                    <label class="formLabel" for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <!-- Password and Confirm Password -->
                <div class="form-group">
                    <label class="formLabel" for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="Password">
                </div>
                <div class="form-group">
                    <label class="formLabel" for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Confirm Password">
                </div>

                <!-- Address Fields -->
                <div class="form-group">
                    <label class="formLabel" for="address">Address</label>
                    <input type="text" id="address" name="address" required placeholder="Street Address">
                </div>
                
                <input type="hidden" id="submitted" name="submitted" value="yes">
                <div class="centerDiv">
                    <button type="submit" class="button" id="submit" name="submit">Register</button>
                </div>
            </form>
        </section>
    

    <!-- Password validation script -->
    <script>
        var password = document.getElementById("password");
        var confirmPassword = document.getElementById("confirmPassword");

        function validatePassword() {
            if (password.value !== confirmPassword.value) {
                confirmPassword.setCustomValidity("Passwords do not match");
            } else {
                confirmPassword.setCustomValidity('');
            }
        }

        password.onchange = validatePassword;
        confirmPassword.onkeyup = validatePassword;
    </script>
    </main>
</body>
</html>
