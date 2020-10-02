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

    //Slumpar fram en av 8 enum-riktningar
    public Direction randomDirection() {
        Random rand = new Random();
        int pick = rand.nextInt(Direction.values().length);
        return Direction.values()[pick];
    }

    public Direction checkBorder() {
        //steg: kolla om positioner är på gräns av gameboard, ev ny direction,

        //kolla om objekt har postion 0 eller 9,
        Direction checkedDirection = getDirection();

        if (getPositionX() == 0 || getPositionX() == 9 || getPositionY() == 0 || getPositionY() == 9) {

            //av de som har 0 eller 9, avgör om de hamnat i hörnet
            if ((getPositionX() == 0 || getPositionX() == 9) && (getPositionY() == 0 || getPositionY() == 9)) {
                //setLastDirection(getDirection());
                checkedDirection = cornerswitch(getPositionX(), getPositionY());
            } else {

                checkedDirection = randomDirection();
            }
        }
        return checkedDirection;
    }

    public void move() {
        setDirection(checkBorder());

        //move on to switch to get new positions med en ny for-loop
        switch (getDirection()) {
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

    //kollar vilket hörn den befinner sig i
    public int decideCorner(int x, int y) {
        int cornerName;

        if (x == 0 && y == 0) {
            cornerName = 1;
        } else if (x == 0 && y == 9) {
            cornerName = 2;
        } else if (x == 9 && y == 9) {
            cornerName = 3;
        } else {
            cornerName = 4;
        }
        return cornerName;
    }


    //ger motsatt riktning beroende på vilket hörn
    public Direction cornerswitch(int x, int y) {
        int cornerName = decideCorner(x, y);
        Direction direction = null;


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


    //GettersNSetters
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPositionX(int x) {
        if (x >= 0 && x <= 9) {
            this.positionX = x;
        }
    }

    public void setPositionY(int y) {
        if (y >= 0 && y <= 9) {
            this.positionY = y;
        }
    }

    public int getPositionX() {                            // Returnera respektive koordinat
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public Direction getDirection() {
        return this.direction;
    }
}