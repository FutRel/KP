package org.example.Tables;

public class Ingridients {
    private int idIngredient;
    private String ingredientName;
    private String ingredientMeasure;
    private double ingredientAmount;

    public Ingridients(int idIngredient, String ingredientName, String ingredientMeasure, double ingredientAmount) {
        this.idIngredient = idIngredient;
        this.ingredientName = ingredientName;
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientAmount = ingredientAmount;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public double getIngredientAmount() {
        return ingredientAmount;
    }
}