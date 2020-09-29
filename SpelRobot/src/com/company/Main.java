package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        //Här börjar loopen som "är" spelet, kör så länge det finns zebror kvar.
        while (ge.getNoOfZebras() != 0) {
            System.out.println();

            //om hungrig gepard eller zebra: tilldelas ny position
            ge.moveIfHungry();

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
    }
}