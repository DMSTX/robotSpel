package com.company;

import java.util.Random;

public class GamePiece {

    private int positionX;
    private int positionY;
    private int startPos; //String, vektor?
    //private int endPos; // kommer nog inte behövas

    //Defaultkonstruktor som sätter random startpositioner
    public GamePiece() {
        Random rand = new Random();
        positionX = rand.nextInt(9); // nåt som kollar att platsen e ledig?
        positionY = rand.nextInt(9);

    }

    // Konstruktorn som deklarerar klassvariablerna (kommer nog inte behövas, men ligger kvar ifall att)
    /*public GamePiece(int x, int y) {
        setPositionX(x);
        setPositionY(y);
    }*/

    //Getter för positionX
    public int getPositionX() {                            // Returnera respektive koordinat
        return this.positionX;
    }

    //Getter för positionY
    public int getPositionY() {
        return this.positionY;
    }

    //Setter för positionX
    public void setPositionX(int x) {
        if (x >= 0 && x <= 9) {
            this.positionX = x;
        }
    }

    //Setter för positionY
    public void setPositionY(int y) {
        if (y >= 0 && y <= 9) {
            this.positionY = y;
        }
    }
    //Returnerar det område som objektet täcker på spelplanen,
    //(så att man kan avgöra om två objekt kolliderar med varandra)
    /*public Square getBounds ( int x, int y){
    return new Square(x, y, 64, 64);
    }*/
}