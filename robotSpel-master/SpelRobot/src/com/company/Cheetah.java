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

    //geparden tar två steg istället för ett med hjälp av @Override
    @Override
    public void subX() {
        if (getPositionX() == 1 || getPositionX() == 8) {
            setPositionX(getPositionX() - 1);
        } else {
            setPositionX(getPositionX() - 2);
        }
    }

    @Override
    public void subY() {
        if (getPositionY() == 1 || getPositionY() == 8) {
            setPositionY(getPositionY() - 1);
        } else {
            setPositionY(getPositionY() - 2);
        }

    }

    @Override
    public void addX() {
        if (getPositionX() == 1 || getPositionX() == 8) {
            setPositionX(getPositionX() + 1);
        } else {
            setPositionX(getPositionX() + 2);
        }
    }

    @Override
    public void addY() {
        if (getPositionY() == 1 || getPositionY() == 8) {
            setPositionY(getPositionY() + 1);
        } else {
            setPositionY(getPositionY() + 2);
        }
    }

    //GettersNSetters
    public int getApetite() {
        return apetite;
    }

    public void setApetite(int apetite) {
        this.apetite = apetite;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public boolean isHungry() {
        return isHungry;
    }
}
