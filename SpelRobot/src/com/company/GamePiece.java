package com.company;

import java.util.Random;

public abstract class GamePiece {
    //Klassmedlemmar
    private int positionX;
    private int positionY;
    private Direction direction; // en enum riktning

    //Defaultkonstruktor som sätter bestämda värden för x och y
    public GamePiece() {
        positionX = 5;
        positionY = 5;
        direction = randomDirection(); // tilldelar en slumpmässig enum Direction
    }

    //Konstruktor med in-parametrar
    public GamePiece(int x, int y) {
        setPositionX(x);
        setPositionY(y);
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
        return direction;
    }

    //Slumpar fram en av 8 enum-riktningar
    public Direction randomDirection() {
        Random rand = new Random();
        int pick = rand.nextInt(Direction.values().length);
        return Direction.values()[pick];
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