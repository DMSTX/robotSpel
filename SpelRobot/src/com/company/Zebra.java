package com.company;

public class Zebra extends GamePiece {
    //Klassmedlemmar
    //private boolean isAlive;

    //Defaultkonstruktor
    public Zebra(){
        super(5, 5);
    }

    public Zebra(int x, int y) { //Konstruktor
        super(x, y);
        //isAlive = true;
    }

    //Setter för isAlive
    /*public void setAlive(boolean alive) {
        this.isAlive = alive;
    }*/

    //Getter för isAlive
    /*public boolean isAlive() {
        return isAlive;
    }*/
}
