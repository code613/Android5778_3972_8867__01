package com.example.ben.android5778_3972_8867__01.model.entities;

import android.location.Location;

import java.sql.Time;

public class Ride {
    //***********************  fields ***********************//
    Client theClient;
    RideStatus currentRideStatus;
    Location startRideLocation;//will be found by GPS
    Location endRideLocation;//will be entered by the rider
    Time startRideTime;//from when picked up??
    Time endRideTime;//from when dropped off??


    //***********************  constructor ***********************//


    public Ride(Client theClient,
                Location startRideLocation,
                Location endRideLocation, Time startRideTime) {
        this.theClient = theClient;
        this.currentRideStatus = RideStatus.WAITING;
        this.startRideLocation = startRideLocation;
        this.endRideLocation = endRideLocation;
        this.startRideTime = startRideTime;
    }




    //***********************  getters / setters ***********************//

    public RideStatus getCurrentRideStatus() {
        return currentRideStatus;
    }

    public void setCurrentRideStatus(RideStatus currentRideStatus) {
        this.currentRideStatus = currentRideStatus;
    }

    public Location getStartRideLocation() {
        return startRideLocation;
    }

    public void setStartRideLocation(Location startRideLocation) {
        this.startRideLocation = startRideLocation;
    }

    public Location getEndRideLocation() {
        return endRideLocation;
    }

    public void setEndRideLocation(Location endRideLocation) {
        this.endRideLocation = endRideLocation;
    }

    public Time getStartRideTime() {
        return startRideTime;
    }

    public void setStartRideTime(Time startRideTime) {
        this.startRideTime = startRideTime;
    }

    public Time getEndRideTime() {
        return endRideTime;
    }

    public void setEndRideTime(Time endRideTime) {
        this.endRideTime = endRideTime;
    }


    //***********************  implementations ***********************//


    @Override
    public String toString() {
        return "Ride{" +
                "currentRideStatus=" + currentRideStatus +
                ", startRideLocation=" + startRideLocation +
                ", endRideLocation=" + endRideLocation +
                ", startRideTime=" + startRideTime +
                ", endRideTime=" + endRideTime +
                '}';
    }
}

//***********************  fields ***********************//


//***********************  constructor ***********************//

//***********************  getters / setters ***********************//

//***********************  implementations ***********************//