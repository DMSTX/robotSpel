package com.company;

public class GamePiece {

    private int startPos;
    private int endPos;


}
public class GamePiece {
    public int x, y;
 
    public GamePiece(int x, int y) {              // Konstruktorn som deklarerar klassvariablerna
        this.x = x;
        this.y = y;
    }
 
    public int getX() {                            // Returnera respektive koordinat 
        return x;
    }
 
    public int getY() {
        return y;
    }
 
    public void setX(int x) {                      // Sätt ett nytt värde på x respektive y
        this.x = x;
    }
 
    public void setY(int y) {
        this.y = y;
    }
 
    public Square getBounds(int x, int y){       //Returnerar det område som objektet täcker på spelplanen,
        return new Square(x, y, 64, 64);         //(så att man kan avgöra om två objekt kolliderar med varandra)
 
}
    //Detta är ett arv som kan användas av alla rörande objekt alltså zebra och cheeta
