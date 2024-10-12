package com.g3app.model;

public class Shipment {
    private int shipmentID;
    private String shipmentDate;
    private String shipmentProgress;
    private String shipmentStatus;
    
    public int getID(){return shipmentID;}
    public String getDate(){return shipmentDate;}
    public String getProgress(){return shipmentProgress;}
    public String getStatus(){return shipmentStatus;}
    
    public void setID(int shipmentID){this.shipmentID = shipmentID;}
    public void setDate(String shipmentDate){this.shipmentDate = shipmentDate;}
    public void setProgress(String shipmentProgress){this.shipmentProgress = shipmentProgress;}
    public void setStatus(String shipmentStatus){this.shipmentStatus = shipmentStatus;}
}
