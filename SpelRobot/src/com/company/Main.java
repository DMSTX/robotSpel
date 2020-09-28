package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatCodePointException;

public class Main {

    public static void main(String[] args) throws IOException {
        GameEngine ge = new GameEngine(); // skapar ett objekt av GameEngine-typ
        System.out.println(ge.menu()); // skriver ut menyn

        ge.setNoOfZebras(ge.readNoOfZebras()); // Sätter antalet Zebror till antal som användaren skriver in
        ge.setNoOfCheetahs(ge.getNoOfZebras()); // Sätter antalet Cheetahs utifrån det antal zebror som skrivs in

        ge.fillWithCheetahs(ge.getNoOfCheetahs(), ge.getNoOfZebras()); // fyller listan med det randomiserade antalet cheetah-objekt
        ge.fillWithZebras(ge.getNoOfZebras()); // fyller listan med det antal zeebra-objekt användaren angivit


        ge.gameBoard.startGameboard(); // kör metoden som ger GameBoard sina prickar

        for (int i = 0; i < ge.gamePieceArray.length; i++){ // loop som placerar djuren på en plats i GameBoard
            ge.gameBoard.placeGamePiece(ge.gamePieceArray[i], ge.gamePieceArray[i].getPositionX(),
                    ge.gamePieceArray[i].getPositionY());
        }
        System.out.println(ge.getNoOfZebras());
        System.out.println(ge.getNoOfCheetahs());
        System.out.println(ge.getOrgChStartX());

        //ge.gameBoard.printBoard(); // skriver ut gameboard med cheetahs och zebror på sina startplatser
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
        while (ge.getNoOfZebras() != 0) {
            System.out.println();
            ge.gameBoard.printBoard(); // skriver ut gameboard med cheetahs och zebror på sina startplatser
            for (int i = 0; i < ge.gamePieceArray.length; i++) { //kollar move-metoden och clear-metoden
                if (ge.gamePieceArray[i] != null){
                    ge.gameBoard.clearBoardPosition(ge.gamePieceArray[i].getPositionX(),
                            ge.gamePieceArray[i].getPositionY());
                    ge.gamePieceArray[i].move();
                }
            }
            //Flytta till gameEngine
            //kollar om objekt cheetah finns på samma position som Zebra, om ja, kill.
            for (int i = 0; i < ge.gamePieceArray.length; i++) {
                if (ge.gamePieceArray[i] != null) {
                    if (ge.gamePieceArray[i] instanceof Cheetah) {
                        for (int j = 0; j < ge.gamePieceArray.length; j++) {
                            if (ge.gamePieceArray[j] != null){
                                if (ge.gamePieceArray[i].getPositionX() ==
                                        ge.gamePieceArray[j].getPositionX()) {
                                    if (ge.gamePieceArray[i].getPositionY() ==
                                            ge.gamePieceArray[j].getPositionY()) {
                                        if (ge.gamePieceArray[j] instanceof Zebra) {
                                            ge.gamePieceArray[j] = null;
                                            ge.setNoOfZebras(ge.getNoOfZebras() - 1);
                                            ((Cheetah) ge.gamePieceArray[i]).setHungry(false);
                                            System.out.println("oppäten");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //Här ska gepard äta zebra

            for (int i = 0; i < ge.gamePieceArray.length; i++) { // loop som placerar djuren på en plats i GameBoard
                if (ge.gamePieceArray[i] != null) {
                    ge.gameBoard.placeGamePiece(ge.gamePieceArray[i], ge.gamePieceArray[i].getPositionX(),
                            ge.gamePieceArray[i].getPositionY());
                }
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            br.readLine();
        }
    }
}