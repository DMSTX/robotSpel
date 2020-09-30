package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
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

    GameBoard gameBoard = new GameBoard(10, 10); // Aggregat
    GamePiece gP = new GamePiece(4,4); //Aggregat-gamepiece för att nå metoder
    GamePiece[] gamePieceArray; //Aggregat

    Stack<Integer> stackX = new Stack<>(); // sätta till private?
    Stack<Integer> stackY = new Stack<>();
    Stack<Integer> chStackY = new Stack<>();

    public GameEngine() {   //defaultkonstruktor
        this.noOfZebras = 0;
        this.noOfCheetahs = 0;
        fillStackX();
        //fillStackY();
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

    //Avgör vilka 3-8 rutor som befinner sig runt ett givet djurs position, returnerar en vektor med deras x-värden
    public ArrayList<Integer> checkSurroundingsX(GameBoard gameBoard, int positionX, int positionY){ // tar in vår gameboard, och positionen x och y
        int width = gameBoard.getWidth();
        int height = gameBoard.getHeigth();

        ArrayList<Integer> xValues = new ArrayList<Integer>();

        for (int j = positionX -1; j <= positionX +1; j ++){
            for (int i = positionY -1; i <= positionY +1; i++){
                if (i >= 0 && j >= 0 && i < height && j < width && !(j == positionX && i == positionY)){
                    xValues.add(j);
                }
            }
        }

        return xValues;
    }

    //Avgör vilka 3-8 rutor som befinner sig runt ett givet djurs position, returnerar en vektor med deras y-värden
    public ArrayList<Integer> checkSurroundingsY(GameBoard gameBoard, int positionX, int positionY){ // tar in vår gameboard, och positionen x och y
        int width = gameBoard.getWidth();
        int height = gameBoard.getHeigth();

        ArrayList<Integer> yValues = new ArrayList<Integer>();

        for (int j = positionX -1; j <= positionX +1; j ++){
            for (int i = positionY -1; i <= positionY +1; i++){
                if (i >= 0 && j >= 0 && i < height && j < width && !(j == positionX && i == positionY)){
                    yValues.add(i);
                }
            }
        }

        return yValues;
    }

    //Kollar om det finns en zebra på nån av de 3-8 positionerna runt en gepard
    public Zebra returnZebraIfClose(ArrayList<Integer> x, ArrayList<Integer> y, GamePiece[] gamePieceArray){
        Zebra victim = null;

        // HÄR KAN FINNAS FEEEEEL

        for (int i = 0; i < gamePieceArray.length ; i++){
            if (gamePieceArray[i] != null){
                if (gamePieceArray[i] instanceof Cheetah){ // HÄr var det instance of zebra förut, det var fel.
                    for (int j = 0; j < x.size() ; j++){
                        if (gamePieceArray[i].getPositionX() == x.get(j)){
                            for (int k = 0; k < y.size() ; k++){
                                if (gamePieceArray[i].getPositionY() == y.get(k)){
                                    victim = new Zebra (gamePieceArray[i].getPositionX(), gamePieceArray[i].getPositionY());
                                    victim.setDirection(gamePieceArray[i].getDirection());
                                    System.out.println("Zebrans position: " + gamePieceArray[i].getPositionX() + ":" +
                                            gamePieceArray[i].getPositionY());
                                    System.out.println("Zebrans riktning: " + gamePieceArray[i].getDirection());
                                    i = gamePieceArray.length;
                                    j = x.size();
                                    k = y.size();
                                }
                            }
                        }
                    }
                }
            }
        }

        return victim;
    }

    //Kollar zebras nästa positions x
    public int checkZebrasNextX(Zebra victim){
        int x = 0;

        switch (victim.getDirection()){
            case up:
            case diagUpLeft:
            case diagUpRight:
                victim.subX();
                x = victim.getPositionX();
                break;
            case down:
            case diagDownLeft:
            case diagDownRight:
                victim.addX();
                x = victim.getPositionX();
                break;
            default:
                x = victim.getPositionX();
        }

        return x;
    }

    //Kollar zebras nästa position y
    public int checkZebrasNextY(Zebra victim){
        int y = 0;

        switch (victim.getDirection()){
            case left:
            case diagDownLeft:
            case diagUpLeft:
                victim.subY();
                y = victim.getPositionY();
                break;
            case right:
            case diagDownRight:
            case diagUpRight:
                victim.addY();
                y = victim.getPositionY();
                break;
            default:
                y = victim.getPositionY();
        }

        return y ;
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
   /* public void fillStackY() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                stackY.push(i);
            }
        }
    }*/

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

    public int startY() {
        Random rand = new Random();
        int startY = rand.nextInt(8) + 1;
        return startY;
    }

    // Getter och setter-metoder


    public int getOrgChStartX() {
        return orgChStartX;
    }

    public void setOrgChStartX(int orgChStartX) {
        this.orgChStartX = orgChStartX;
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



}

