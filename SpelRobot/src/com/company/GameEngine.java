package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

enum direction { //regler för hur en spelpjäs kan flytta sig
    //up (positionX -1, PositionY), //måste innehålla positioner x,y och begränsnigar
    down,
    left,
    right,
    diagUpLeft,
    diagUpRight,
    diagDownLeft,
    diagDownRight
}

public class GameEngine {

    // Klassmedlemmar
    private int noOfZebras;
    private int noOfCheetahs;

    private GameBoard gameBoard = new GameBoard(); //Aggregat
    private GamePiece gamePiece = new GamePiece(); //Aggregat

    ArrayList<Zebra> zebraList = new ArrayList<Zebra>(); // sätta till private?
    ArrayList<Cheetah> cheetahList = new ArrayList<Cheetah>(); // sätta till private?

    public GameEngine() {   //defaultkonstruktor
        this.noOfZebras = 0;
        this.noOfCheetahs = 0;
    }

    // Medlemsmetoder
    public String menu() {  // Skriver ut menyn i början av spelet
        String s = "Välkommen till spelet!\n" +
                "För att avsluta tryck 0.\n"; // obs att i nuläget så avslutas inte programmet om man skriver 0
        return s;
    }

    public int readNoOfZebras() { //prövar att läsa in en int från användaren och kollar att den är mellan 1 och 10.
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

    public void fillZebraList(int noOfZebras) { // Fyller en lista med zebror
        for (int i = 0; i < noOfZebras; i++) {
            zebraList.add(new Zebra());
        }
    }

    public void fillCheetahList(int noOfCheetahs) { // Fyller en lista med zebror
        for (int i = 0; i < noOfCheetahs; i++) {
            cheetahList.add(new Cheetah());
        }
    }

    public void removeZebras() {
        this.noOfZebras--;
    } // Minskar antalet zebror med 1


    // Getter och setter-metoder
    public int getNoOfZebras() {
        return noOfZebras;
    }

    public int getNoOfCheetahs() {
        return noOfCheetahs;
    }

    public void setNoOfCheetahs(int noOfZebras) { // Randomiserar antalet geparder mellan 1 och en mindre än antalet zebror
        Random rand = new Random();
        if (noOfZebras == 1){
            this.noOfCheetahs = 1;
        } else {
            this.noOfCheetahs = rand.nextInt(noOfZebras - 1) + 1;
        }
    }

    public void setNoOfZebras(int noOfZebras) {
        this.noOfZebras = noOfZebras;
    }
}