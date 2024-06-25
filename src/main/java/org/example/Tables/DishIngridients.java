package org.example.Tables;

public class DishIngridients {
    private int idDish;
    private int idIngredient;
    private int ingredientAmount;

    public DishIngridients(int idDish, int idIngredient, int ingredientAmount) {
        this.idDish = idDish;
        this.idIngredient = idIngredient;
        this.ingredientAmount = ingredientAmount;
    }

    public int getIdDish() {
        return idDish;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public int getIngredientAmount() {
        return ingredientAmount;
    }

    public void setIngredientAmount(int ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }
}