package com.example.ben.android5778_3972_8867__01.model.backend;

public class BackendFactory {
    private static final BackendFactory ourInstance = new BackendFactory();

    public static BackendFactory getInstance() {
        return ourInstance;
    }

    private BackendFactory() {
    }
}
