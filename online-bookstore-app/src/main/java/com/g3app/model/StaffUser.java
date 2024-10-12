package com.g3app.model;

public final class StaffUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dob;
    private String phone;
    private String address;
    private String city;
    private String postcode;
    private String country;
    private String role;
    private String accountStatus;
    private int staffId; // Auto-assigned for new users

    // Constructor for adding new users (without staffId)
    public StaffUser(String firstName, String lastName, String email, String password, String dob, String phone, String address, String city, String postcode, String country, String role, String accountStatus) {
        setFirstName(firstName);  // Call setter for validation
        setLastName(lastName);
        setEmail(email);          // Call setter for validation
        setPassword(password);    // Call setter for validation
        setDob(dob);
        setPhone(phone);
        setAddress(address);
        setCity(city);
        setPostcode(postcode);
        setCountry(country);
        setRole(role);
        setAccountStatus(accountStatus);
    }

    // Constructor for updating existing users (with staffId)
    public StaffUser(String firstName, String lastName, String email, String password, String dob, String phone, 
                     String address, String city, String postcode, String country, String role, String accountStatus, int staffId) {
        this(firstName, lastName, email, password, dob, phone, address, city, postcode, country, role, accountStatus);
        this.staffId = staffId;
    }

    // Getters and setters with validation

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
