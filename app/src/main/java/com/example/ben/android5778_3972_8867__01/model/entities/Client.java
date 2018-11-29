package com.example.ben.android5778_3972_8867__01.model.entities;

public class Client {
    //***********************  fields ***********************//
    String clientName;
    String clientPhoneNumber;
    String clientEmailAddress;

    //***********************  constructor ***********************//
    public Client(String clientName, String clientPhoneNumber, String clientEmailAddress) {
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientEmailAddress = clientEmailAddress;
    }

    //***********************  getters / setters ***********************//
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
