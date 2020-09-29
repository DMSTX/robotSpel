package com.company;

public class Cheetah extends GamePiece {
    // Klassmedlemmar
    private boolean isHungry;
    private int apetite;

    //Defaultkonstruktor
    public Cheetah() {
        super(5, 5);
        isHungry = true;
        setApetite(0);
    }

    //Konstruktor med parametrar
    public Cheetah(int x, int y) {
        super(x, y);
        isHungry = true;
    }

    public int getApetite() {
        return apetite;
    }

    public void setApetite(int apetite) {
        this.apetite = apetite;
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
