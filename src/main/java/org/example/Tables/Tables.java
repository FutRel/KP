package org.example.Tables;

public class Tables {
    private int idTable;
    private int capacityTable;
    private int employTable;

    public Tables(int idTable, int capacityTable, int employTable) {
        this.idTable = idTable;
        this.capacityTable = capacityTable;
        this.employTable = employTable;
    }

    public int getIdTable() {
        return idTable;
    }

    public int getCapacityTable() {
        return capacityTable;
    }

}