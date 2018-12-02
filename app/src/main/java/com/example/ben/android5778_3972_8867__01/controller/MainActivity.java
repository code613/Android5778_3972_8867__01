package com.example.ben.android5778_3972_8867__01.controller;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ben.android5778_3972_8867__01.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        DatabaseReference myRef1 = myRef.child("students");
        myRef1.push().setValue("student message_a");
        myRef1.push().setValue("student message 2","");
        myRef1.push().setValue("student message888","");
        String key = myRef1.push().getKey();
        myRef1.child(key).setValue("student 181message 1");
    }
}
