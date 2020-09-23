package com.company;

import java.util.Random;

public class GamePiece {

    private int positionX;
    private int positionY;
    private int startPos; //String, vektor?
    //private int endPos; // kommer nog inte behövas


    public GamePiece (){  //default konstruktor som sätter random startpositioner
        Random rand = new Random();
        positionX = rand.nextInt(9); // nåt som kollar att platsen e ledig
        positionY = rand.nextInt(9);

    }
    /*public GamePiece(int x, int y) {              // Konstruktorn som deklarerar klassvariablerna
        setPositionX(x);
        setPositionY(y);
    }*/

    public int getPositionX() {                            // Returnera respektive koordinat
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public void setPositionX(int x) {                      // Sätt ett nytt värde på x respektive y
        if (x >= 0 && x <= 9) {
            this.positionX = x;
        }
    }

    public void setPositionY(int y) {
        if (y >= 0 && y <= 9) {
            this.positionY = y;
        }
    }

        //public Square getBounds ( int x, int y){       //Returnerar det område som objektet täcker på spelplanen,
            //return new Square(x, y, 64, 64);         //(så att man kan avgöra om två objekt kolliderar med varandra)

        //}
//Detta är ett arv som kan användas av alla rörande objekt alltså zebra och cheeta
    }