package com.company;

public class GameBoard {

    //Klassmedlemmar
    public char zebra = 'z';
    public char cheetah = 'c';
    public char[][] gameboard = new char[10][10];

    //noOfZebras?

    //Default konstruktor
    public GameBoard() {
        startGameboard();
    }

    //Metod som fyller spelplan med punkter
    public void startGameboard() {
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                gameboard[j][j] = '.';
            }
            gameboard[i][i] = '.';
        }
    }

    //Metod som skriver ut spelplan
    public void printBoard() {
        //startGameboard();
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                System.out.print(gameboard[j][j]);
            }
            System.out.println(gameboard[i][i]);
        }
    }

    //Metod för att placera Zebror
    public void placeGamePieceZ(int x, int y) { //Placera gamepieces Z
        GamePiece test = new GamePiece(x, y); //association till gamepiece för att hämta positioner x och y
        this.gameboard[test.getPositionX()][test.getPositionY()] = zebra;
        System.out.println(gameboard[x][y]); //testa utskrift för position x och y
        printBoard();
    }
}




