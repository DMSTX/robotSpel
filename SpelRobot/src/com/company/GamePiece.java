package com.company;

import java.util.Random;

public class GamePiece {
    //Klassmedlemmar
    private int positionX;
    private int positionY;
    private Direction direction; // en enum riktning

    //Defaultkonstruktor som sätter random startpositioner och riktning
    public GamePiece() {
        Random rand = new Random();
        positionX = rand.nextInt(9); // Här behövs kontrollstruktur för att de inte ska hamna på samma plats!
        positionY = rand.nextInt(9);
        direction = randomDirection(); // tilldelar en slumpmässig enum Direction
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

    /*//
    DESSA ANVÄNDS INTE I NULÄGET
    Setter för positionX
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
    }*/
}