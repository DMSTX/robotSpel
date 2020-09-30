package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        GameEngine ge = new GameEngine(); // skapar ett objekt av GameEngine-typ
        System.out.println(ge.menu()); // skriver ut menyn

        ge.setNoOfZebras(ge.readNoOfZebras()); // Sätter antalet Zebror till antal som användaren skriver in
        ge.setNoOfCheetahs(ge.getNoOfZebras()); // Sätter antalet Cheetahs utifrån det antal zebror som skrivs in

        ge.fillWithCheetahs(ge.getNoOfCheetahs(), ge.getNoOfZebras()); // fyller listan med det randomiserade antalet cheetah-objekt
        ge.fillWithZebras(ge.getNoOfZebras()); // fyller listan med det antal zeebra-objekt användaren angivit

        // ger GameBoard sina prickar
        ge.gameBoard.startGameboard();

        // skapar bräde med djur på sina startpositioner
        ge.makeStartBoard();

        //skriver ut bräde med startpositionerna
        ge.gameBoard.printBoard();

        //väntar på knapptryck
        ge.readKey();

        //testutskrift av metoderna som kollar ett djurs omkringliggande positioner



        //Här börjar loopen som "är" spelet, kör så länge det finns zebror kvar.
        while (ge.getNoOfZebras() != 0) {
            System.out.println();

            //om hungrig gepard eller zebra: tilldelas ny position
            ge.moveIfHungry();

            //hunt här
            for (int i = 0; i < ge.gamePieceArray.length; i++){
                if (ge.gamePieceArray[i] instanceof Cheetah){
                    if (((Cheetah) ge.gamePieceArray[i]).isHungry() == true){

                        //andropar checkSurroundingsX och sparar dess return
                        ArrayList<Integer> copyXArrayList = new ArrayList<Integer>();
                        copyXArrayList = ge.checkSurroundingsX(ge.gameBoard, ge.gamePieceArray[i].getPositionX(), ge.gamePieceArray[i].getPositionY());

                        //anropar checkSurroundingsY och sparar dess return
                        ArrayList<Integer> copyYArrayList = new ArrayList<Integer>();
                        copyYArrayList = ge.checkSurroundingsX(ge.gameBoard, ge.gamePieceArray[i].getPositionX(), ge.gamePieceArray[i].getPositionY());

                        //anropar returnZebraIfClose och sparar dess return
                        Zebra victimZebra = new Zebra();
                        victimZebra = ge.returnZebraIfClose(copyXArrayList, copyYArrayList, ge.gamePieceArray);

                        // FEL!! GEPARDEN JAGAR SIN SVANS!
                        // OCH utskrifter där det står zebra handlar om geparder

                        //anropar checkZebrasNextX och sparar dess return OM victimZebra inte är null
                        if (victimZebra != null) {
                            int nextX = ge.checkZebrasNextX(victimZebra);
                            int nextY = ge.checkZebrasNextY(victimZebra);

                            //Sätter gepardens position till samma som victimZebrans nästa
                            ge.gamePieceArray[i].setPositionX(nextX);
                            ge.gamePieceArray[i].setPositionY(nextY);

                            //Testutskrift
                            System.out.println("victimZebrans position är " + victimZebra.getPositionX() + ":" +
                                    victimZebra.getPositionY() + " och dess riktning är " + victimZebra.getDirection());
                            System.out.println("gepardens nya hunt-position är " + ge.gamePieceArray[i].getPositionX() +
                                    ":" + ge.gamePieceArray[i].getPositionY());
                        }
                    }
                }
            }

            // Här kollas om en gepard står på samma ställe som en zebra, isåfall dödas zebran
            ge.kill();

            //placerar djuren på en plats i GameBoard
            ge.placeOnGameBoard();

            // skriver ut brädet
            ge.gameBoard.printBoard();

            //Ge tillbaka hungern till mätta geparder
            ge.giveHunger();

            //Väntar på knapptryck från användaren för att gå vidare till nästa varv i loopen
            ge.readKey();
        }

        System.out.println("Spelet är slut!");

        // Testutskrift för att kolla antalet zebror, geparder och gepardernas start-rad.
        /*System.out.println(ge.getNoOfZebras());
        System.out.println(ge.getNoOfCheetahs());
        System.out.println(ge.getOrgChStartX());*/

        // skriver ut gameboard med cheetahs och zebror på sina startplatser
        //ge.gameBoard.printBoard();
/*
        for (int i = 0; i < ge.getNoOfZebras(); i++) { //testutskrift för att kolla zebrornas direction-värden
            System.out.print("Zebra riktning: ");
            System.out.println(ge.gamePieceArray[i].getDirection());
        }
*/
        /*for (int i = 0; i < ge.gamePieceArray.length; i++) { //testutskrift för att kolla gepardernas direction-värden
            System.out.println("djur");
            System.out.print(ge.gamePieceArray[i].getPositionX());
            System.out.println(ge.gamePieceArray[i].getPositionY());*/


            /*for (int i = ge.getNoOfZebras(); i < ge.gamePieceArray.length; i ++){
                System.out.println("gepardens position: " + ge.gamePieceArray[i].getPositionX() + ":" + ge.gamePieceArray[i].getPositionY());

                ge.directionOfZebraIfClose(ge.checkSurroundingsX(ge.gameBoard, ge.gamePieceArray[i].getPositionX(), ge.gamePieceArray[i].getPositionY()),
                        ge.checkSurroundingsY(ge.gameBoard, ge.gamePieceArray[i].getPositionX(), ge.gamePieceArray[i].getPositionY()),
                        ge.gamePieceArray);
            }*/
    }
}