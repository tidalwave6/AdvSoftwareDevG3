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


                <div class="form-group">
                    <label class="formLabel" for="dob">Date of Birth</label>
                    <input type="date" id="dob" name="dob" required>
                </div>

                <!-- Country code dropdown and phone number in the same line -->
                <div class="form-group">
                    <label class="formLabel" for="phone">Phone Number</label>
                    <div class="phone-group">
                        <select id="countryCode" name="countryCode" required>
                            <option value="+1">+1 (USA)</option>
                            <option value="+44">+44 (UK)</option>
                            <option value="+61">+61 (Australia)</option>
                            <option value="+91">+91 (India)</option>
                            <option value="+81">+81 (Japan)</option>
                            <option value="+49">+49 (Germany)</option>
                            <option value="+33">+33 (France)</option>
                        </select>
                        <input type="tel" id="phone" name="phone" required placeholder="Phone Number">
                    </div>
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
                <div class="form-group">
                    <label class="formLabel" for="city">City</label>
                    <input type="text" id="city" name="city" required placeholder="City">
                </div>
                <div class="form-group">
                    <label class="formLabel" for="postcode">Postcode</label>
                    <input type="text" id="postcode" name="postcode" required placeholder="Postcode">
                </div>
                <div class="form-group">
                    <label class="formLabel" for="country">Country</label>
                    <input type="text" id="country" name="country" required placeholder="Country">
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
