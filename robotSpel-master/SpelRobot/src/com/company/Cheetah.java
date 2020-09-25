package com.company;

public class Cheetah extends GamePiece {
    // Klassmedlemmar
    private boolean isHungry;

    //Defaultkonstruktor
    public Cheetah() {
        super(5, 5);
        isHungry = true;
    }

    //Konstruktor med parametrar
    public Cheetah(int x, int y) {
        super(x, y);
        isHungry = true;
    }

    //Setter för isHungry
    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    //Getter för isHungry
    public boolean isHungry() {
        return isHungry;
    }
}
