package com.example.ben.android5778_3972_8867__01.model.datasource;

import android.content.Context;

import com.example.ben.android5778_3972_8867__01.model.backend.Backend;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase_DBManager implements Backend {

    Context mContext;
    DatabaseReference fireBaseRoot;
    //GetCurrentLocation gl;

    ///todo dont want firebase to be public, so that cant creat it. only backend can.
    public Firebase_DBManager() {
        fireBaseRoot = FirebaseDatabase.getInstance().getReference("Rides");
    }


    @Override
    public void addRide(String currentLocation, String destinationLoaction, String email, String phone) {

    }
}
