package com.company;

import java.util.Random;

public class GamePiece {
    //Klassmedlemmar
    private int positionX;
    private int positionY;
    private Direction direction; // en enum riktning
    private Direction lastDirection;


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

    public void move() {
        //steg: kolla om positioner är på gräns av gameboard, ev ny direction,
        //töm förra position, lägg till ny Z/C på nästa steg i gameboard.


        //kolla om objekt har postion 0 eller 9,
        if (getPositionX() == 0 || getPositionX() == 9 || getPositionY() == 0 || getPositionY() == 9) {

               //av de som har 0 eller 9, avgör om de hamnat i hörnet
            if ((getPositionX() == 0 || getPositionX() == 9) && (getPositionY() == 0 || getPositionY() == 9)) {
                //setLastDirection(getDirection());
                direction = conerswitch(getPositionX(), getPositionY());
                System.out.println("Skamvrån!");

            }

            direction = randomDirection();
            System.out.println(getDirection());
        }

        //move on to switch to get new positions med en ny for-loop
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

        }
    }

    public Direction conerswitch (int x, int y) {
        int cornerName;
        Direction direction= null;

        if (x == 0 && y == 0) {
            cornerName = 1;
        } else if (x == 0 && y == 9) {
            cornerName = 2;
        } else if (x == 9 && y == 9) {
            cornerName = 3;
        } else {
            cornerName = 4;
        }


        switch (cornerName) {
            case 1:
                direction = Direction.diagDownRight;
                break;
            case 2:
                direction = Direction.diagDownLeft;
                break;
            case 3:
                direction = Direction.diagUpLeft;
                break;
            case 4:
                direction = Direction.diagUpRight;
                break;


        }
        return direction;
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
    public void subX() {
        setPositionX(getPositionX() - 1);
    }

    // Metod som ökar värdet hos ett objekt med 1
    public void addX() {
        setPositionX(getPositionX() + 1);
    }

    //Setter för direction
    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public Direction getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(Direction lastDirection) {

        this.lastDirection = lastDirection;
    }
}