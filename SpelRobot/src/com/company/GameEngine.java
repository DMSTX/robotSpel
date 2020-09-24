package com.company;

import java.util.*;

enum Direction { //regler för hur en spelpjäs kan flytta sig
    down,
    left,
    right,
    diagUpLeft,
    diagUpRight,
    diagDownLeft,
    diagDownRight;
}

public class GameEngine {

    // Klassmedlemmar
    private int noOfZebras;
    private int noOfCheetahs;

    private GameBoard gameBoard = new GameBoard(); //Aggregat
    GamePiece[] gamePieceArray = new GamePiece[noOfZebras+noOfCheetahs]; //Aggregat

    // ArrayList<Zebra> zebraList = new ArrayList<Zebra>(); // sätta till private?
    // ArrayList<Cheetah> cheetahList = new ArrayList<Cheetah>(); // sätta till private?

    Stack<Integer> stackX = new Stack<>(); // sätta till private?
    Stack<Integer> stackY = new Stack<>(); // sätta till private?

    public GameEngine() {   //defaultkonstruktor
        this.noOfZebras = 0;
        this.noOfCheetahs = 0;
        fillStackX();
        fillStackY();
        shuffleStackX();
        shuffleStackY();
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

    public void fillWithZebras(int noOfZebras){
        for (int i = 0; i < noOfZebras; i ++) {
            gamePieceArray[i] = new Zebra(firstStackValue(), firstStackValue());
        }
    }

    public void fillWithCheetahs(int noOfCheetahs, int noOfZebras){
        for (int i = noOfZebras; i < (noOfCheetahs + noOfZebras); i ++) {
            gamePieceArray[i] = new Cheetah(firstStackValue(), firstStackValue());
        }
    }

    /*public void fillZebraList(int noOfZebras) { // Fyller en lista med zebror
        for (int i = 0; i < noOfZebras; i++) {
            zebraList.add(new Zebra());
        }
    }

    public void fillCheetahList(int noOfCheetahs) { // Fyller en lista med geparder
        for (int i = 0; i < noOfCheetahs; i++) {
            cheetahList.add(new Cheetah());
        }
    }*/

    //fyller stack för X-kordinat
    public void fillStackX() {
        for (int i = 0; i < 10; i++) {
            stackX.push(i);
        }
    }

    //fyller stack för Y-kordinat
    public void fillStackY() {
        for (int i = 0; i < 10; i++) {
            stackY.push(i);
        }
    }

    //blandar stackX
    public void shuffleStackX() {
        Collections.shuffle(stackX);
    }

    //blandar stackY
    public void shuffleStackY() {
        Collections.shuffle(stackY);
    }

    public int firstStackValue() {
        return stackX.pop();
    }

    // Getter och setter-metoder
    public int getNoOfZebras() {
        return noOfZebras;
    }

    public int getNoOfCheetahs() {
        return noOfCheetahs;
    }

    public void setNoOfCheetahs(int noOfZebras) { // Randomiserar antalet geparder mellan 1 och en mindre än antalet zebror
        Random rand = new Random();
        if (noOfZebras == 1) {
            this.noOfCheetahs = 1;
        } else {
            this.noOfCheetahs = rand.nextInt(noOfZebras - 1) + 1;
        }
    }

    public void setNoOfZebras(int noOfZebras) {
        this.noOfZebras = noOfZebras;
    }

// ANVÄNDS INTE ÄN SÅ LÄNGE
    // public void removeZebras() { // Minskar antalet zebror med 1
    //        this.noOfZebras--;
    //    }
}