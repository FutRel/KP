package org.example.Tables;

public class Clients {
    private int idClient;
    private String nameClient;
    private String addressClient;

    public Clients(int idClient, String nameClient, String addressClient) {
        this.idClient = idClient;
        this.nameClient = nameClient;
        this.addressClient = addressClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getAddressClient() {
        return addressClient;
    }

}