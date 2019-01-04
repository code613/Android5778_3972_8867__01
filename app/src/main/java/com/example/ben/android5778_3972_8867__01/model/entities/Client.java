package com.example.ben.android5778_3972_8867__01.model.entities;

import android.location.Location;
import java.sql.Time;
import java.sql.Timestamp;

public class Client {
    //***********************  fields ***********************//
    Long id;
    String clientName;
    String clientPhoneNumber;
    String clientEmailAddress;


    Long driverId;

    Locationf startPoint;//location
    Locationf destinationPoint;//location

    Time startTime;
    Time endTime;
    String time;
    Timestampf tstamp;

    ClientRequestStatus status;

    //***********************  constructor ***********************//
    public Client() {}
    public Client(String clientName, String clientPhoneNumber, String clientEmailAddress) {
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientEmailAddress = clientEmailAddress;
    }
    public Client(String s) {
        this.clientName=s;
    }

    //***********************  getters / setters ***********************//


    public Long getClientId() {
        return id;
    }
    public void setClientId(Long id) {
        this.id = id;
    }


    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }
    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientEmailAddress() {
        return clientEmailAddress;
    }
    public void setClientEmailAddress(String clientEmailAddress) {
        this.clientEmailAddress = clientEmailAddress;
    }

    //____________________________________________________________________________________________//

    public Long getDriverId() {
        return driverId;
    }
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    //____________________________________________________________________________________________//

    public Time getStartTime() {
        return startTime;
    }
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getTime() {        return time;    }
    public void setTime(String t) {        this.time = t;    }

    public Timestampf getTstamp() {
        return tstamp;
    }

    public void setTstamp(Timestampf tstamp) {
        this.tstamp = tstamp;
    }
    //____________________________________________________________________________________________//

    public Location getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(Locationf startPoint) {
        this.startPoint = startPoint;
    }

    public Location getDestinationPoint() {
        return destinationPoint;
    }
    public void setDestinationPoint(Locationf destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    //____________________________________________________________________________________________//

    public ClientRequestStatus getStatus() {
        return status;
    }
    public void setStatus(ClientRequestStatus status) {
        this.status = status;
    }

    //***********************  implementations ***********************//
    @Override
    public String toString() {
        return "Client{" +
                "clientName='" + clientName + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                ", clientEmailAddress='" + clientEmailAddress + '\'' +
                '}';
    }


}
