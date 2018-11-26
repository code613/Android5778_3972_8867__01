package com.example.ben.android5778_3972_8867__01.model.entities;

import android.location.Location;

import java.sql.Time;

public class Ride {
    //***********************  fields ***********************//
    RideStatus currentRidestatus;
    Location startRideLocation;//will be found by GPS
    Location endRideLocation;//will be entered by the rider
    Time startRideTime;//from when picked up??
    Time endRideTime;//from when dropped off??

}
