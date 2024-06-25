package org.example.Tables;

public class Waiters {
    private int idWaiter;
    private String nameWaiter;
    private String addressWaiter;
    private String numberWaiter;

    public Waiters(int idWaiter, String nameWaiter, String addressWaiter, String numberWaiter) {
        this.idWaiter = idWaiter;
        this.nameWaiter = nameWaiter;
        this.addressWaiter = addressWaiter;
        this.numberWaiter = numberWaiter;
    }

    public int getIdWaiter() {
        return idWaiter;
    }

    public String getNameWaiter() {
        return nameWaiter;
    }

    public String getAddressWaiter() {
        return addressWaiter;
    }

    public String getNumberWaiter() {
        return numberWaiter;
    }

}