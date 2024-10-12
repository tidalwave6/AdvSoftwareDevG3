package com.g3app.model;

import java.util.Date;

public class SupportTicket {
    private int ticketId;
    private String customerName;
    private String email;
    private String subjectTitle;
    private String typeOfEnquiry;
    private String issueDescription;
    private String status;
    private Date dateSubmitted;

    // Constructor to initialize all fields
    public SupportTicket(int ticketId, String customerName, String email, String subjectTitle, String typeOfEnquiry, String issueDescription, String status, Date dateSubmitted) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.email = email;
        this.subjectTitle = subjectTitle;
        this.typeOfEnquiry = typeOfEnquiry;
        this.issueDescription = issueDescription;
        this.status = status;
        this.dateSubmitted = dateSubmitted;
    }
    
    // Getters and Setters
    public int getTicketId() {
        return ticketId; 
    }
    
    public void setTicketId(int ticketId) { 
        this.ticketId = ticketId; 
    }
    
    public String getCustomerName() { 
        return customerName; 
    }
    
    public void setCustomerName(String customerName) { 
        this.customerName = customerName; 
    }
    
    public String getEmail() { 
        return email; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public String getSubjectTitle() { 
        return subjectTitle; 
    }
    
    public void setSubjectTitle(String subjectTitle) { 
        this.subjectTitle = subjectTitle; 
    }
    
    public String getTypeOfEnquiry() { 
        return typeOfEnquiry; 
    }
    
    public void setTypeOfEnquiry(String typeOfEnquiry) { 
        this.typeOfEnquiry = typeOfEnquiry; 
    }
    
    public String getIssueDescription() { 
        return issueDescription; 
    }
    
    public void setIssueDescription(String issueDescription) { 
        this.issueDescription = issueDescription; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public Date getDateSubmitted() { 
        return dateSubmitted; 
    }
    
    public void setDateSubmitted(Date dateSubmitted) { 
        this.dateSubmitted = dateSubmitted; 
    }
}