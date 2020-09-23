package com.company;

public class Zebra extends GamePiece {
    //Klassmedlemmar
    boolean isAlive;

    //Defaultkonstruktor
    public Zebra() { //Konstruktor
        isAlive = true;
    }

    //Setter för isAlive
    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    //Getter för isAlive
    public boolean isAlive() {
        return isAlive;
    }
}
