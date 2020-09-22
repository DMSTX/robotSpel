package com.company;

public class GameBoard {

    public char zebra = 'z';
    public char cheetah = 'c';
    public char[][] gameboard = new char[10][10];

    //public int positionZ; //var ska positionerna hämtas från?
    //public int positionC;
    //noOfZebras?

    public GameBoard() {
        startGameboard();
    }


    public void startGameboard() {
        for (int i = 0; i < this.gameboard.length; i++) {
            for (int j = 0; j < this.gameboard.length; j++) {
                this.gameboard[j][j] = '.';
            }
            this.gameboard[i][i] = '.';
        }
    }

    public void printBoard() {   //Skriver ut gameboard
        startGameboard();
        for (int i = 0; i < this.gameboard.length; i++) {
            for (int j = 0; j < this.gameboard.length; j++) {
                System.out.print(gameboard[j][j]);
            }
            System.out.println(gameboard[i][i]);
        }

        public void placeGamePiece ( int positionZ, int positionC){ //Placera gamepieces Z och C på gameboard

        }
    }
}


