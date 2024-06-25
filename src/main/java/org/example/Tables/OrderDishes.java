package org.example.Tables;

public class OrderDishes {
    private int idOrder;
    private int dishOrder;
    private int dishAmountOrder;

    public OrderDishes(int idOrder, int dishOrder, int dishAmountOrder) {
        this.idOrder = idOrder;
        this.dishOrder = dishOrder;
        this.dishAmountOrder = dishAmountOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getDishOrder() {
        return dishOrder;
    }

    public int getDishAmountOrder() {
        return dishAmountOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setDishOrder(int dishOrder) {
        this.dishOrder = dishOrder;
    }

    public void setDishAmountOrder(int dishAmountOrder) {
        this.dishAmountOrder = dishAmountOrder;
    }
}