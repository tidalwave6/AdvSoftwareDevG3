package com.g3app.model;

public class User {
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

    // Constructor
    public User(String firstName, String lastName, String email, String password, String dob, String phone, String address, String city, String postcode, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email; 
        this.password = password;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
}
