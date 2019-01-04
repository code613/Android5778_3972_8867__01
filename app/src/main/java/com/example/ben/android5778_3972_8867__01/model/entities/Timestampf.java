package com.example.ben.android5778_3972_8867__01.model.entities;

import java.sql.Timestamp;

public class Timestampf extends Timestamp {

    public  static Long time=Long.valueOf(0);
    Timestampf()
    {
        super(time);
    }

    public Timestampf(long time) {
        super(time);

    }
}
