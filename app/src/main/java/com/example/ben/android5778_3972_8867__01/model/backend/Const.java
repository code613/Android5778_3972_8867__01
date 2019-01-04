package com.example.ben.android5778_3972_8867__01.model.backend;

import android.content.ContentValues;

import com.example.ben.android5778_3972_8867__01.model.entities.Client;

public class Const
{


    public static class ClientConst {
        public static final String NAME = "name";
        public static final String ID ="id" ;
        public static final String PHONE = "phone";
        public static final String EMAIL ="eMail" ;
    }


    public static ContentValues CourseToContentValues(Client client) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(ClientConst.NAME, client.getClientName());
        contentValues.put(ClientConst.ID, client.getClientId());
        contentValues.put(ClientConst.PHONE, client.getClientPhoneNumber());
        contentValues.put(ClientConst.EMAIL, client.getClientEmailAddress());





        return contentValues;
    }




    public static Client  ContentValuesToCourse(ContentValues contentValues) {

        Client client = new Client();

        client.setClientName(contentValues.getAsString(ClientConst.NAME));
        client.setClientId(contentValues.getAsLong(ClientConst.ID));
        client.setClientPhoneNumber(contentValues.getAsString(ClientConst.PHONE));
        client.setClientPhoneNumber(contentValues.getAsString(ClientConst.EMAIL));

        return client;

    }

}
