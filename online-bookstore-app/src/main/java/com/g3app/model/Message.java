package com.g3app.model;

import java.sql.Timestamp;

public class Message {
    private int messageId;
    private int ticketId;
    private String sender;
    private String message;
    private Timestamp timestamp;

    // Constructors, Getters, and Setters
    public Message(int messageId, int ticketId, String sender, String message, Timestamp timestamp) {
        this.messageId = messageId;
        this.ticketId = ticketId;
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
