package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        GameEngine ge = new GameEngine(); // skapar ett objekt av GameEngine-typ
        System.out.println(ge.menu()); // skriver ut menyn

        ge.setNoOfZebras(ge.readNoOfZebras()); // Sätter antalet Zebror till antal som användaren skriver in
        if (ge.getNoOfZebras() > 0) {           // om input är 0 så avslutas spelet
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
        }
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

    }
}