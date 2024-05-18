package com.example.onestopmedicalsolution;

public class Hospital {
    private String name;
    private String phoneNumber;
    private String av_seats;

    public Hospital(String name, String phoneNumber, String av_seats) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.av_seats = av_seats;
    }

    public Hospital() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAv_seats() {
        return av_seats;
    }

    public void setAv_seats(String av_seats) {
        this.av_seats = av_seats;
    }
}
