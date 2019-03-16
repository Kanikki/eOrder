package com.niki.eorder;

public class DataPassing {
    private static DataPassing instance;
    private String location;
    private String standID;
    private int seatNumber;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStandID() {
        return standID;
    }

    public void setStandID(String standID) {
        this.standID = standID;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public static DataPassing getInstance(){
        if (instance == null){
            instance = new DataPassing();
        }
        return instance;
    }
}
