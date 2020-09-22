package com.company;

public class GamePiece {

    private int startPos;
    private int endPos;


}
public class GameObject {
    public int x, y;
 
    public GameObject(int x, int y) {              // Konstruktorn som deklarerar klassvariablerna
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
 
    public Rectangle getBounds(int x, int y){       //Returnerar det område som objektet täcker på spelplanen,
        return new Rectangle(x, y, 64, 64);         //(så att man kan avgöra om två objekt kolliderar med varandra)
 
}
