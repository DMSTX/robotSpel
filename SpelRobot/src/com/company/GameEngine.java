package com.company;

import java.util.*;

enum Direction { //8 riktningar som tilldelas en GamePiece när den skapas
    up,
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
    private int chStartX;
    private int orgChStartX;

    //De fyra objekten nedan går ej att nå från Main ifall de får en åtkomstmodifierare.
    //Vet ej varför men det är därför de inte har någon.
    GameBoard gameBoard = new GameBoard(10, 10); // Aggregat
    GamePiece gamePiece = new GamePiece();
    GamePiece[] gamePieceArray; //Aggregat

    Stack<Integer> stackX = new Stack<>(); // sätta till private?
    Stack<Integer> stackY = new Stack<>(); // sätta till private?
    Stack<Integer> chStackY = new Stack<>();//sätta till private?

    public GameEngine() {   //defaultkonstruktor
        this.noOfZebras = 0;
        this.noOfCheetahs = 0;
        fillStackX();
        fillStackY();
        fillChStackY();
        shuffleStackX();
        shuffleStackY();
        shuffleChStackY();
        setOrgChStartX(createChStartX());
        setChStartX(reserveChStartX(getOrgChStartX()));
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

    public void fillWithZebras(int noOfZebras) {
        for (int i = 0; i < noOfZebras; i++) {
            gamePieceArray[i] = new Zebra(firstStackValueX(), firstStackValueY());
        }
    }

    public void fillWithCheetahs(int noOfCheetahs, int noOfZebras) {
        gamePieceArray = new GamePiece[noOfZebras + noOfCheetahs];
        for (int i = noOfZebras; i < (noOfCheetahs + noOfZebras); i++) {
            gamePieceArray[i] = new Cheetah(getOrgChStartX(), firstChStackValueY());
        }
    }

    //fyller stack för X-kordinat
    public void fillStackX() {
        for (int i = 0; i < 10; i++) {
            stackX.push(i);
        }
    }
    public void addToStackX(int i){
        stackX.push(i);
    }

    //fyller stack för Y-kordinat
    public void fillStackY() {
        for (int i = 0; i < 10; i++) {
            for (int j=0; j < 10; j++){
                stackY.push(i);}
            }
        }

    //fyller stack för cheetah
    public void fillChStackY(){
        for (int i =0; i <10; i++){
            chStackY.push(i);
        }
    }

    //blandar chStackY
    public void  shuffleChStackY(){
        Collections.shuffle(chStackY);
    }
    //blandar stackX
    public void shuffleStackX() {
        Collections.shuffle(stackX);
    }

    //blandar stackY
    public void shuffleStackY() {
        Collections.shuffle(stackY);
    }

    // Hämtar översta värdet i stacken för X-värden
    public int firstStackValueX() {
        return stackX.pop();
    }

    // Hämtar översta värdet i stacken för Y-värden
    public int firstStackValueY() {
        return stackY.pop();
    }

    // Hämtar översta värdet i stacken för cheetah Y-värden
    public int firstChStackValueY(){ return chStackY.pop();}

    // Tar första värdet ur Zebrornas x-stack och ger det till geparderna
    public int createChStartX(){
        int chSX=(stackX.pop());
        return chSX;
    }
    // Tar gepardernas x-värde och ökar eller minskar med 1, och skickar tillbaka till zebrornas x-stack
    public int reserveChStartX(int chSX) {
        if (chSX<9){
            chSX++;
            addToStackX(chSX);
        }
        else {
            chSX--;
            addToStackX(chSX);}
        return chSX;
    }

    // Getter och setter-metoder
    public int getOrgChStartX() {
        return orgChStartX;
    }

    public void setOrgChStartX(int orgChStartX) {
        this.orgChStartX = orgChStartX;
    }

    public int getChStartX() {
        return chStartX;
    } // kanske att denna inte behövs? den anropas aldrig.

    public void setChStartX(int chStartX) {
        this.chStartX = chStartX;
    }

    public int getNoOfZebras() {
        return noOfZebras;
    }

    public int getNoOfCheetahs() {
        return noOfCheetahs;
    }

    public void setNoOfZebras(int noOfZebras) {
        this.noOfZebras = noOfZebras;
    }

    public void setNoOfCheetahs(int noOfZebras) { // Randomiserar antalet geparder mellan 1 och en mindre än antalet zebror
        Random rand = new Random();
        if (noOfZebras == 1) {
            this.noOfCheetahs = 1;
        } else {
            this.noOfCheetahs = rand.nextInt(noOfZebras - 1) + 1;
        }
    }

    // ANVÄNDS INTE ÄN SÅ LÄNGE

    // public void removeZebras() { // Minskar antalet zebror med 1
    //        this.noOfZebras--;
    //    }


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
}