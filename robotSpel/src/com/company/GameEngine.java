package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameEngine {

    // Klassmedlemmar
    private int noOfZebras;
    private int noOfCheetahs;

    private GameBoard gameBoard = new GameBoard(getNoOfZebras());

    ArrayList<Zebra> zebraList = new ArrayList<Zebra>(); // sätta till private?
    ArrayList<Cheetah> cheetahList = new ArrayList<Cheetah>(); // sätta till private?

    public GameEngine() { //defaultkonstruktor
        this.noOfZebras = 10;
        this.noOfCheetahs = 3;
    }

    public GameEngine(int noOfZebras) { // konstruktor med variabler
        this.noOfZebras = noOfZebras;
        this.noOfCheetahs = noOfZebras - 1; // här vill vi randomisera antalet geparder
    }

    // Medlemsmetoder

    public void fillZebraList(int noOfZebras) { // Fyller en lista med zebror
        for (int i = 0; i < noOfZebras; i++) {
            zebraList.add(new Zebra(2, 4)); // Här skickar den med två låtsasvärden till konstruktorn
        }
    }

    public String menu() { // Skriver ut menyn i början av spelet
        String s = "Välkommen till spelet!\n" +
                "För att avsluta tryck 0.\n";
        return s;
    }

    public int readNoOfZebras() { // prövar att läsa in en int från användaren och kollar att den är mellan 1 och 10.
        boolean x = true;
        int n = 0;
        do {
            System.out.print("Skriv in antal zebror, max 10 stycken: ");
            try {
                Scanner scan = new Scanner(System.in);
                n = scan.nextInt();
                x = false;
            } catch (InputMismatchException e) {
                System.out.println("Det där var inte ett heltal!");
            } catch (Exception e) {
                System.out.println("Oväntat fel, du måste skriva in ett heltal!");
            }
            if (n > 10 || n < 0) {
                System.out.println("Hallå, max 10 zebror och inga negativa zebror, tack!");
                x = true;
            }
        } while (x);



        return n;
    }

    public void removeZebras() {
        this.noOfZebras--;
    }

    // Getter och setter-metoder
    public int getNoOfZebras() {
        return noOfZebras;
    }

    public int getNoOfCheetahs() {
        return noOfCheetahs;
    }

    public void setNoOfCheetahs(int noOfCheetahs) {
        this.noOfCheetahs = noOfCheetahs;
    }

    public void setNoOfZebras(int noOfZebras) {
        this.noOfZebras = noOfZebras;
    }
}


