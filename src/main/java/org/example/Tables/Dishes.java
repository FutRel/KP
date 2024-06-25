package org.example.Tables;

public class Dishes {
    private int idDish;
    private String nameDish;

    public Dishes(int idDish, String nameDish) {
        this.idDish = idDish;
        this.nameDish = nameDish;
    }

    public int getIdDish() {
        return idDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }
}
