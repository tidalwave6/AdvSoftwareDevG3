<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.g3app.model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>My Details - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp"/>
    <% User user = (User) request.getSession().getAttribute("user"); %>
    <main>
        <section class="contact-section animated">
            <% String name = user.getFirstName(); %>
            <h1><% out.println(name + "'s"); %> Details</h1>
            <p>Here you can view your details as well as change them if you want.</p>

            <div class="tabs">
                <div class="tab active" data-target="dashboard">My Account Details</div>
                <div class="tab" data-target="editAccount">Edit my Account</div>
            </div>
            
            <div id="dashboard" class="tab-content active">
                <h1>My Account Details</h1>
                <div class="account-info">
                    <div class="account-field">
                        <label><b>Name:</b>
                        <% if (user != null) {
                            String firstName = user.getFirstName();
                            String lastName = user.getLastName();
                            out.println("<a>" + " " + firstName + " " + lastName);
                        } %></label>
                    </div>

                    <div class="account-field">
                        <label><b>Email:</b>
                        <% if (user != null) {
                            String email = user.getEmail();
                            out.println("<a>" + " " + email);
                        } %></label>
                    </div>
                    <div class="account-field">
                        <label for="dateSubmitted"><b>Date of Birth:</b>
                        <% if (user != null) {
                            String dob = user.getDob();
                            out.println("<a>" + " " + dob);
                        } %></label>
                    </div>
                    <div class="account-field">
                        <label for="status"><b>Phone Number:</b>
                        <% if (user != null) {
                            String phone = user.getPhone();
                            out.println("<a>" + " " + phone);
                        } %></label>
                    </div>
                    <div class="account-field">
                        <label for="type"><b>Address:</b>
                        <% if (user != null) {
                            String address = user.getAddress();
                            String city = user.getCity();
                            String postcode = user.getPostcode();
                            String country = user.getCountry();
                            out.println("<a>" + " " + address + " " + city + " " + postcode + " " + country);
                        } %></label>
                    </div>
                    <div class="account-field">
                        <label for="description"><b>Membership:</b></label>
                        <span id="status">None.</span>
                    </div>
                    
                    <div class="form-group">
                        <form action="DeleteAccountServlet" method="post" onsubmit="return confirm('Are you sure you want to delete your account? This cannot be undone.');">
                            <button type="submit" class="delete-button">Delete Account</button>
                        </form>
                    </div>
                </div>
            </div>
            
            <div id="editAccount" class="tab-content">
                <h2>Edit Account Details</h2>
                <form id="editAccountForm" action="UpdateAccountServlet" method="post">
                    <div class="form-group">
                        <label for="firstName">First Name:</label>
                        <input type="text" id="firstName" name="firstName" value="<%= user.getFirstName() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="lastName">Last Name:</label>
                        <input type="text" id="lastName" name="lastName" value="<%= user.getLastName() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="dob">Date of Birth:</label>
                        <input type="date" id="dob" name="dob" value="<%= user.getDob() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" id="phone" name="phone" value="<%= user.getPhone() %>" required>
                        <small style="color: red;" id="phoneWarning" hidden>Phone number can only be numbers.</small>
                    </div>

                    <div class="form-group">
                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address" value="<%= user.getAddress() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="city">City:</label>
                        <input type="text" id="city" name="city" value="<%= user.getCity() %>" required>
                    </div>

                    <div class="form-group">
                        <label for="postcode">Postcode:</label>
                        <input type="text" id="postcode" name="postcode" value="<%= user.getPostcode() %>" pattern="\d*" title="Postcode must contain digits only" required>
                        <small style="color: red;" id="postcodeWarning" hidden>Postcode can only be numbers.</small>
                    </div>

                    <div class="form-group">
                        <label for="country">Country:</label>
                        <input type="text" id="country" name="country" value="<%= user.getCountry() %>" required>
                    </div>                   

                    <div class="form-group">
                        <label for="password">New Password:</label>
                        <input type="password" id="password" name="password">
                    </div>

                    <% String status = request.getParameter("status"); %>
                    <% if ("error".equals(status)) { %>
                        <div class="error-message">
                            <p style="color: red;">An error occurred while processing your request. Please try again.</p>
                        </div>
                    <% } %>
                    
                    <div class="form-group">
                        <button type="submit" id="editAccountButton" disabled>Update</button>
                    </div>
                </form>
            </div>
        </section>
    </main>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            function validateForm(formId, buttonId) {
                const form = document.getElementById(formId);
                const submitButton = document.getElementById(buttonId);
                const inputs = form.querySelectorAll('input[required], textarea[required], select[required]');
                const postcodeInput = document.getElementById('postcode');
                const postcodeWarning = document.getElementById('postcodeWarning');
                const phoneInput = document.getElementById('phone');
                const phoneWarning = document.getElementById('phoneWarning');
                
                postcodeInput.addEventListener('input', () => {
                    const isValid = /^[0-9]*$/.test(postcodeInput.value);
                    postcodeWarning.hidden = isValid;
                });
                
                phoneInput.addEventListener('input', () => {
                    const isValid = /^[0-9]*$/.test(phoneInput.value);
                    phoneWarning.hidden = isValid;
                });

                function updateButtonState() {
                    let allValid = true;

                    inputs.forEach(input => {
                        if (input.type === 'select-one') {
                            if (!input.value.trim()) {
                                allValid = false;
                            }
                        } else {
                            if (!input.value.trim()) {
                                allValid = false;
                            }
                        }
                    });

                    submitButton.disabled = !allValid;
                    submitButton.style.backgroundColor = allValid ? '#4CAF50' : '#ccc';
                }

                form.addEventListener('input', updateButtonState);
                form.addEventListener('change', updateButtonState);
                updateButtonState();
            }

            validateForm('editAccountForm', 'editAccountButton');

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
</body>
</html>
