package com.example.ben.android5778_3972_8867__01.model.backend;

import com.example.ben.android5778_3972_8867__01.model.datasource.Firebase_DBManager;

public class BackendFactory {
    //private static final BackendFactory ourInstance = new BackendFactory();

    private static /*final*/ Firebase_DBManager ourInstance;

    public static Firebase_DBManager getInstance() {
        return ourInstance;
    }

    //public static BackendFactory getInstance() {return ourInstance; }

    public BackendFactory(){
        if (ourInstance == null)
            ourInstance = new Firebase_DBManager();
    }
}
