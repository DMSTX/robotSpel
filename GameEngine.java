package com.company;

import java.util.*;

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

    Stack<Integer> stackX = new Stack<>();
    Stack<Integer> stackY = new Stack<>();

    public GameEngine() { //defaultkonstruktor //DENNA KOMMER I NULÄGET ALLTID ANROPAS!
        this.noOfZebras = 10;
        this.noOfCheetahs = 3;
    }

    //LOGISKT FEL HÄR!!
    // ett GameEngine-objekt måste skapas för att kunna skriva ut menyn,
    // men det är först i menyn vi ber användaren skriva in antal zebror
    // Så i nuläget är det omöjligt att kalla på denna konstruktorn.
    public GameEngine(int noOfZebras) { // konstruktor med variabler
        this.noOfZebras = noOfZebras;
        this.noOfCheetahs = noOfZebras - 1; // här vill vi randomisera antalet geparder
    }

    // Medlemsmetoder
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



    public void fillZebraList(int noOfZebras) { // Fyller en lista med zebror
        for (int i = 0; i < noOfZebras; i++) {
            zebraList.add(new Zebra());
        }
    }

    //fyller stack för X-kordinat
    public void fillStackX(){
        for (int i=0; i<10; i++){
            stackX.push(i);
        }
    }
    //fyller stack för Y-kordinat
    public void fillStackY(){
        for (int i=0; i<10; i++){
            stackY.push(i);
        }
    }
    //blandar stackX
    public void shuffleStackX(){
        Collections.shuffle(stackX);
    }
    //blandar stackY
    public void shuffleStackY(){
        Collections.shuffle(stackY);
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

    public void setNoOfCheetahs(int noOfCheetahs) {
        this.noOfCheetahs = noOfCheetahs;
    }

    public void setNoOfZebras(int noOfZebras) {
        this.noOfZebras = noOfZebras;
    }


}