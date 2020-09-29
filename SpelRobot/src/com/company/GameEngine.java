package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

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

    // väntar på knapptryck innan nästa steg sker
    public void readKey() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
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

    //Fyller på med zebror i vektorn med gamePieces
    public void fillWithZebras(int noOfZebras) {
        for (int i = 0; i < noOfZebras; i++) {
            gamePieceArray[i] = new Zebra(firstStackValueX(), startY());
        }
    }

    //Fyller på med geparder i vektorn med gamePieces
    public void fillWithCheetahs(int noOfCheetahs, int noOfZebras) {
        gamePieceArray = new GamePiece[noOfZebras + noOfCheetahs];
        for (int i = noOfZebras; i < (noOfCheetahs + noOfZebras); i++) {
            gamePieceArray[i] = new Cheetah(getOrgChStartX(), firstChStackValueY());
        }
    }

    //placerar djuren på en ny tilldelad plats i GameBoard
    public void placeOnGameBoard(){
        for (int i = 0; i < gamePieceArray.length; i++) {
            if (gamePieceArray[i] != null) {
                gameBoard.placeGamePiece(gamePieceArray[i], gamePieceArray[i].getPositionX(),
                        gamePieceArray[i].getPositionY());
            }
        }
    }

    //Sättar djur på sina startpositioner i GameBoard
    public void makeStartBoard(){
        for (int i = 0; i < gamePieceArray.length; i++){ // loop som placerar djuren på sin startplats i GameBoard
            gameBoard.placeGamePiece(gamePieceArray[i], gamePieceArray[i].getPositionX(),
                    gamePieceArray[i].getPositionY());
        }
    }

    //kollar om objekt cheetah finns på samma position som Zebra, om ja, kill.
    public void kill() throws InterruptedException {
        for (int i = 0; i < gamePieceArray.length; i++) {
            if (gamePieceArray[i] != null) {
                if (gamePieceArray[i] instanceof Cheetah) {
                    for (int j = 0; j < gamePieceArray.length; j++) {
                        if (gamePieceArray[j] != null) {
                            if (gamePieceArray[i].getPositionX() ==
                                    gamePieceArray[j].getPositionX()) {
                                if (gamePieceArray[i].getPositionY() ==
                                        gamePieceArray[j].getPositionY()) {
                                    if (gamePieceArray[j] instanceof Zebra) {
                                        gamePieceArray[j] = null;
                                        setNoOfZebras(getNoOfZebras() - 1);
                                        ((Cheetah) gamePieceArray[i]).setHungry(false);
                                        //TimeUnit.SECONDS.sleep(1);
                                        System.out.println("\nEn zebra har blivit oppäten!"); // i utskrift som sker NU vill vi ha stort C
                                        System.out.println("Nu finns det " + getNoOfZebras() + " zebror kvar.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //om hungrig gepard eller zebra: tilldelas ny position
    public void moveIfHungry(){
        for (int i = 0; i < gamePieceArray.length; i++) {
            if (gamePieceArray[i] != null){
                if (gamePieceArray[i] instanceof Cheetah) {
                    if (((Cheetah) gamePieceArray[i]).isHungry() == false) {
                        System.out.println("cheeta säger: ja e mätt");
                        //skriva ut c men stora C
                        continue;
                    }
                }
                gameBoard.clearBoardPosition(gamePieceArray[i].getPositionX(),
                        gamePieceArray[i].getPositionY());
                gamePieceArray[i].move();
            }
        }
    }

    // ger tillbaka hungern på den/de mätta geparden/geparderna
    public void giveHunger() {
        for (int i = 0; i < gamePieceArray.length; i++) { //kollar move-metoden och clear-metoden
            if (gamePieceArray[i] != null) {
                if (gamePieceArray[i] instanceof Cheetah) {
                    if (((Cheetah) gamePieceArray[i]).isHungry() == false) {

                        if (((Cheetah) gamePieceArray[i]).getApetite() == 2) {
                            ((Cheetah) gamePieceArray[i]).setApetite(0);
                            ((Cheetah) gamePieceArray[i]).setHungry(true);
                            System.out.println("nu har jag fått tillbaka min hunger");
                        } else if (((Cheetah) gamePieceArray[i]).getApetite() < 2) {
                            ((Cheetah) gamePieceArray[i]).setApetite(((Cheetah)
                                    gamePieceArray[i]).getApetite() + 1);
                        }
                    }
                }
            }
        }
    }

    //fyller stack för zebrornas X-kordinat
    public void fillStackX() {
        for (int i = 0; i < 10; i++) {
            stackX.push(i);
        }
    }

    //lägger till kort i zebrornas X-stack
    public void addToStackX(int i) {
        stackX.push(i);
    }

    //fyller stack för zebrornas Y-kordinat
    public void fillStackY() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                stackY.push(i);
            }
        }
    }

    //fyller Y-stack för cheetah
    public void fillChStackY() {
        for (int i = 0; i < 10; i++) {
            chStackY.push(i);
        }
    }

    //blandar chStackY
    public void shuffleChStackY() {
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
    public int firstChStackValueY() {
        return chStackY.pop();
    }

    public int createChStartX() {
        int chSX = (stackX.pop());
        return chSX;
    }

    public int reserveChStartX(int chSX) {

        if (chSX < 9) {
            chSX++;
            addToStackX(chSX);
        } else {
            chSX--;
            addToStackX(chSX);
        }
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
    }

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

    public int startY() {
        Random rand = new Random();
        int startY = rand.nextInt(8) + 1;
        return startY;
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

