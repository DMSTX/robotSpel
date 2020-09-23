package com.company;

public class Cheetah extends GamePiece {
    // Klassmedlemmar
    boolean isHungry;

    //Defaultkonstruktor
    public Cheetah(){
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
