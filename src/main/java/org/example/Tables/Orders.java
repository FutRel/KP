package org.example.Tables;

import java.sql.Date;
import java.sql.Time;

public class Orders {
    private int idOrder;
    private Date dateOrder;
    private Time timeStartOrder;
    private Time timeEndOrder;
    private int waiterOrder;
    private int tableOrder;
    private int clientOrder;

    public Orders(int idOrder, Date dateOrder, Time timeStartOrder, Time timeEndOrder, int waiterOrder, int tableOrder, int clientOrder) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.timeStartOrder = timeStartOrder;
        this.timeEndOrder = timeEndOrder;
        this.waiterOrder = waiterOrder;
        this.tableOrder = tableOrder;
        this.clientOrder = clientOrder;
    }

    // Getters and Setters
    public int getIdOrder() {
        return idOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Time getTimeStartOrder() {
        return timeStartOrder;
    }

    public void setTimeStartOrder(Time timeStartOrder) {
        this.timeStartOrder = timeStartOrder;
    }

    public Time getTimeEndOrder() {
        return timeEndOrder;
    }

    public int getWaiterOrder() {
        return waiterOrder;
    }

    public int getTableOrder() {
        return tableOrder;
    }

    public void setTableOrder(int tableOrder) {
        this.tableOrder = tableOrder;
    }

    public int getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(int clientOrder) {
        this.clientOrder = clientOrder;
    }
}