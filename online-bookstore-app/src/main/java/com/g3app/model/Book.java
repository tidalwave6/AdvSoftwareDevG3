package com.g3app.model;

public class Book {
    private int bookId; // ID will be auto-incremented by the database
    private String title;
    private String author;
    private double price;
    private String publishedDate;
    private String description;
    private String imgUrl;
    private String genre; // Property for genre
    private String medium; // Property for medium

    // Constructor for creating a new book (without bookId)
    public Book(String title, String author, double price, String publishedDate, String description, String imgUrl, String genre, String medium) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishedDate = publishedDate;
        this.description = description;
        this.imgUrl = imgUrl;
        this.genre = genre; // Initialize genre
        this.medium = medium; // Initialize medium
    }

    // Constructor for retrieving a book from the database (with bookId)
    public Book(int bookId, String title, String author, double price, String publishedDate, String description, String imgUrl, String genre, String medium) {
        this.bookId = bookId; // Initialize bookId
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishedDate = publishedDate;
        this.description = description;
        this.imgUrl = imgUrl;
        this.genre = genre; // Initialize genre
        this.medium = medium; // Initialize medium
    }

    // Getters and Setters
    public int getBookId() { // Getter for bookId
        return bookId;
    }

    public void setBookId(int bookId) { // Setter for bookId
        this.bookId = bookId;
    }

    public String getTitle() { 
        return title; 
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() { 
        return author; 
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() { 
        return price; 
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublishedDate() { 
        return publishedDate; 
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() { 
        return description; 
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
