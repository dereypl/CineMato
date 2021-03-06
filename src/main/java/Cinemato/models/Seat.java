package main.java.Cinemato.models;

public class Seat {

    private int Id;
    private String Row;
    private int Number;
    private boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public Seat(int id, String row, int number) {
        Id = id;
        Row = row;
        Number = number;
        available = true;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRow() {
        return Row;
    }

    public void setRow(String row) {
        Row = row;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String toString() {
        return Id + "&" + Row + "&" + Number;
    }
}
