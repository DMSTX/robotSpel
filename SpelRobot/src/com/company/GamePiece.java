package com.company;

import java.util.Random;

public class GamePiece {
    //Klassmedlemmar
    private int positionX;
    private int positionY;
    private Direction direction; // en enum riktning

    //Defaultkonstruktor som sätter bestämda värden för x och y
    public GamePiece() {
        positionX = 5;
        positionY = 5;
        setDirection(randomDirection());
    }

    //Konstruktor med in-parametrar
    public GamePiece(int x, int y) {
        setPositionX(x);
        setPositionY(y);
        setDirection(randomDirection()); // tilldelar en slumpmässig enum Direction
    }

    //Getter för positionX
    public int getPositionX() {                            // Returnera respektive koordinat
        return this.positionX;
    }

    //Getter för positionY
    public int getPositionY() {
        return this.positionY;
    }

    //Getter för direction
    public Direction getDirection() {
        return this.direction;
    }

    //Slumpar fram en av 8 enum-riktningar
    public Direction randomDirection() {
        Random rand = new Random();
        int pick = rand.nextInt(Direction.values().length);
        return Direction.values()[pick];
    }

    public void move(){
        
        switch (getDirection()){
            case up:
                subX();
                break;
            case down:
                addX();
                break;
            case left:
                subY();
                break;
            case right:
                addY();
                break;
            case diagUpLeft:
                subX();
                subY();
                break;
            case diagUpRight:
                subX();
                addY();
                break;
            case diagDownLeft:
                addX();
                subY();
                break;
            case diagDownRight:
                addX();
                addY();
                break;
            default:
        }
    }

    // Metod för att minska y-värdet hos ett objekt med 1
    public void subY() {
        setPositionY(getPositionY() - 1);
    }

    // Metod som ökar Y-värdet hos ett objekt med 1
    public void addY() {
        setPositionY(getPositionY() + 1);
    }

    // Metod som minskar värdet hos ett objekt med 1
    public void subX(){
        setPositionX(getPositionX() - 1);
    }

    // Metod som ökar värdet hos ett objekt med 1
    public void addX(){
        setPositionX(getPositionX() + 1);
    }

    //Setter för direction
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //Setter för positionX
    public void setPositionX(int x) {
        this.positionX = x;
    }

    //Setter för positionY
    public void setPositionY(int y) {
        if (y >= 0 && y <= 9) {
            this.positionY = y;
        }
    }
}