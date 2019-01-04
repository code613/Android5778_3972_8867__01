package com.example.ben.android5778_3972_8867__01.model.backend;

import android.content.ContentValues;
import android.location.Location;

import com.example.ben.android5778_3972_8867__01.model.datasource.Utils;
import com.example.ben.android5778_3972_8867__01.model.entities.Client;

import java.util.List;

public interface Backend {

    Long addClient(ContentValues client, Location a, Location b, Utils.Action<Long> action);
    boolean removeClient(Long id);
    boolean updateClient(Long id, ContentValues values);
    List<Client> getClients();





}

