package com.company;

public class Main {

    public static void main(String[] args) {
        GameEngine ge = new GameEngine(); // skapar ett objekt av GameEngine-typ
        System.out.println(ge.menu()); // skriver ut menyn

        ge.setNoOfZebras(ge.readNoOfZebras()); // Sätter antalet Zebror till antal som användaren skriver in
        ge.setNoOfCheetahs(ge.getNoOfZebras()); // Sätter antalet Cheetahs utifrån det antal zebror som skrivs in

        ge.fillWithZebras(ge.getNoOfZebras()); // fyller listan med det antal zeebra-objekt användaren angivit
        ge.fillWithCheetahs(ge.getNoOfCheetahs(), ge.getNoOfZebras()); // fyller listan med det randomiserade antalet cheetah-objekt

        ge.gameBoard.startGameboard(); // kör metoden som ger GameBoard sina prickar

        for (int i = 0; i < ge.gamePieceArray.length; i++){ // loop som placerar djuren på en plats i GameBoard
            ge.gameBoard.placeGamePiece(ge.gamePieceArray[i], ge.gamePieceArray[i].getPositionX(),
                    ge.gamePieceArray[i].getPositionY());
        }

        ge.gameBoard.printBoard(); // skriver ut gameboard med cheetahs och zebror på sina startplatser
/*
        for (int i = 0; i < ge.getNoOfZebras(); i++) { //testutskrift för att kolla zebrornas direction-värden
            System.out.print("Zebra riktning: ");
            System.out.println(ge.gamePieceArray[i].getDirection());
        }

        for (int i = ge.getNoOfZebras(); i < (ge.getNoOfZebras() + ge.getNoOfCheetahs()); i++) { //testutskrift för att kolla gepardernas direction-värden
            System.out.print("Gepard riktning: ");
            System.out.println(ge.gamePieceArray[i].getDirection());
        }*/
    }
}